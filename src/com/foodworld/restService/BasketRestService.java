package com.foodworld.restService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodworld.pojos.Basket;
import com.foodworld.pojos.BasketItem;
import com.foodworld.pojos.BasketItemVO;
import com.foodworld.pojos.Product;
import com.foodworld.pojos.User;
import com.foodworld.resources.HibernateUtil;

@Path("/Basket")
public class BasketRestService {
	
//	POST http://localhost:8080/FoodWorldAPI/Basket/Add?userId=sharmiliarveti&productId=1
	@POST
	@Path("/Add")
	@Produces(MediaType.TEXT_PLAIN)
	public String addToBasket(@QueryParam("userId") String userId, @QueryParam("productId") Integer productId) {
		String msg = "";
		Session  session = HibernateUtil.getSessionfactory().openSession();
		Transaction transaction = session.beginTransaction();
		Criteria criteria =  session.createCriteria(User.class);
		criteria.add(Restrictions.eq("userId",userId));
		Object result = criteria.uniqueResult();
		User user = (User) result;
		Query query = session.createQuery("FROM Basket where user_id = " + user.getId());
		Object result2 = query.uniqueResult();
		Basket basket = (Basket) result2;
		Query query2 = session.createQuery("FROM BasketItem where product_productId = " + productId + " and basketId = " + basket.getBasketId());
		Object result3 = query2.uniqueResult();
		if(result3 != null) {
			BasketItem basketItem = (BasketItem) result3;
			basketItem.setQuantity(basketItem.getQuantity() + 1);
			session.save(basketItem);
		}else {
			criteria =  session.createCriteria(Product.class);
			criteria.add(Restrictions.eq("productId", productId));
			Object result4 = criteria.uniqueResult();
			Product product = (Product) result4;
			
			BasketItem basketItem = new BasketItem();
			basketItem.setProduct(product);
			basketItem.setQuantity(1);
			basketItem.setBasket(basket);
			session.save(basketItem);
		}
		transaction.commit();
		session.close();
		msg = "Product added to Basket successfully";
		return msg;
	}
	
//	GET http://localhost:8080/FoodWorldAPI/Basket/Fetch?userId=sharmiliarveti
	@GET
	@Path("/Fetch")
	@Produces(MediaType.APPLICATION_JSON)
	public String fetchBasket(@QueryParam("userId") String userId) {
		String arrayToJson = "";
		try {
			Session  session = HibernateUtil.getSessionfactory().openSession();
			Criteria criteria =  session.createCriteria(User.class);
			criteria.add(Restrictions.eq("userId",userId));
			Object result = criteria.uniqueResult();
			User user = (User) result;
			Query query = session.createQuery("FROM Basket where user_id = " + user.getId());
			Object result2 = query.uniqueResult();
			Basket basket = (Basket) result2;
			List<BasketItemVO> basketItemVOs = new ArrayList<BasketItemVO>();
			for (BasketItem basketItem : basket.getBasketItems()) {
				BasketItemVO basketItemVO = new BasketItemVO();
				basketItemVO.setQuantity(basketItem.getQuantity());
				basketItemVO.setProductId(basketItem.getProduct().getProductId());
				basketItemVO.setProductName(basketItem.getProduct().getProductName());
				basketItemVO.setProductPrice(basketItem.getProduct().getPrice());
				Long totalPrice = Math.multiplyFull(basketItem.getQuantity(), basketItem.getProduct().getPrice().intValueExact());
				basketItemVO.setTotalPrice(totalPrice);
				basketItemVOs.add(basketItemVO);
			}
			ObjectMapper objectMapper = new ObjectMapper();
		    	arrayToJson = objectMapper.writeValueAsString(basketItemVOs);
    	
		}catch (Exception e) {

		}
		return arrayToJson;
	}

}
