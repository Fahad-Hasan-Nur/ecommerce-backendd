package com.spring.ecommerce.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.LogLikelihoodSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.ecommerce.dto.ProductDto;
import com.spring.ecommerce.model.Product;
import com.spring.ecommerce.model.Rating;
import com.spring.ecommerce.repository.ProductRepo;
import com.spring.ecommerce.repository.RatingRepo;

import lombok.RequiredArgsConstructor;

/*************************************************************************
 * {@link RecommendationService} implementation class
 * 
 * @author Fahad Hasan
 * @since 2021-4-26
 *************************************************************************/
@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RecommendationServiceImpl implements RecommendationService {

	private final ProductRepo productRepo;
	private final RatingRepo ratingRepo;
	private HashMap<String, String> idBuilder = new HashMap<String, String>();

	/*************************************************************************
	 * Get List of Recommended Product {@link Product}
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException
	 * @throws IOException
	 *************************************************************************/

	public List<ProductDto> getItemBasedRecommendedProduct(String productId) throws IOException, TasteException {
		createDataset();
		return itemBasedRecommendation(productId);
	}

	/*************************************************************************
	 * Get List of Recommended Product {@link Product}
	 * 
	 * @return {@link List<Product>}
	 * @throws TasteException
	 * @throws NumberFormatException
	 * @throws IOException
	 *************************************************************************/

	public List<ProductDto> getUserBasedRecommendedProduct(String userId)
			throws NumberFormatException, TasteException, IOException {
		createDataset();
		return userBasedRecommendation(userId);
	}
	
	/*************************************************************************
	 * {@link RecommendationService} implementation method
	 * 
	 *************************************************************************/

	private List<ProductDto> itemBasedRecommendation(String productId) throws IOException, TasteException {
		List<ProductDto> productList = new ArrayList<>();

		String data = "data/data.csv";
		File file = new File(data);

		DataModel dataModel = new FileDataModel(file);
		ItemSimilarity sim = new LogLikelihoodSimilarity(dataModel);
		GenericItemBasedRecommender rec = new GenericItemBasedRecommender(dataModel, sim);
		List<RecommendedItem> recommendations = rec.mostSimilarItems(makeUuid(productId).getMostSignificantBits(), 5);
		for (RecommendedItem recommendation : recommendations) {
			long id = recommendation.getItemID();
			productList.add(getProductById(id));
		}
		file.delete();
		idBuilder.clear();
		return productList;
	}
	/*************************************************************************
	 * {@link userBasedRecommendation} implementation method
	 * 
	 *************************************************************************/

	private List<ProductDto> userBasedRecommendation(String userId) throws NumberFormatException, TasteException {
		List<ProductDto> productList = new ArrayList<>();
		File file = new File("data/data.csv");
		DataModel model = null;
		LogLikelihoodSimilarity similarity = null;
		UserNeighborhood neighberhood = null;
		UserBasedRecommender recommender = null;

		try {
			model = new FileDataModel(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		similarity = new LogLikelihoodSimilarity(model);
		neighberhood = new ThresholdUserNeighborhood(0.1, similarity, model);
		recommender = new GenericUserBasedRecommender(model, neighberhood, similarity);

		List<RecommendedItem> recommenderItem;
		recommenderItem = recommender.recommend(makeUuid(userId).getMostSignificantBits(), 5);

		for (RecommendedItem r : recommenderItem) {
			long productId = r.getItemID();
			productList.add(getProductById(productId));
		}
		file.delete();
		idBuilder.clear();
		return productList;
	}
	
	

	private ProductDto getProjectDtoFromEntity(Product ob) {
		ob.setBrandId(ob.getBrand().getId());
		ob.setBrandName(ob.getBrand().getName());
		ob.setCategoryId(ob.getCategory().getId());
		ob.setCategoryName(ob.getCategory().getName());
		ob.setSubCategoryId(ob.getSubCategory().getId());
		ob.setSubCategoryName(ob.getSubCategory().getName());
		ob.setImageId(ob.getImage().getId());
		ob.setImageName(ob.getImage().getName());
		ProductDto obj = new ProductDto();
		BeanUtils.copyProperties(ob, obj);
		return obj;
	}

	private void createDataset() throws IOException {
		List<Rating> rating = ratingRepo.findAll();
		BufferedWriter wr = new BufferedWriter(new FileWriter("data/data.csv"));
		for (Rating ob : rating) {
			UUID idUser = makeUuid(ob.getUserId());
			long msbU = idUser.getMostSignificantBits();
			long lsbU = idUser.getLeastSignificantBits();
			UUID idProduct = makeUuid(ob.getProductId());
			long msbP = idProduct.getMostSignificantBits();
			long lsbP = idProduct.getLeastSignificantBits();
			wr.write(msbU + "," + msbP + "," + ob.getRatingValue() + "\n");
			idBuilder.put(String.valueOf(msbU),String.valueOf(lsbU));
			idBuilder.put(String.valueOf(msbP),String.valueOf(lsbP));

		}
		wr.close();

	}

	private ProductDto getProductById(long id) {
		String productId = getProductIdFromDataSet(id);
		return productRepo.findById(productId).map(this::getProjectDtoFromEntity).orElse(null);
	}

	private UUID makeUuid(String uuidString) {
		UUID uuid = new UUID(new BigInteger(uuidString.substring(0, 16), 16).longValue(),
				new BigInteger(uuidString.substring(16), 16).longValue());
		return uuid;
	}
	
	private String getProductIdFromDataSet(long msb) {
		String lsb=idBuilder.get(String.valueOf(msb));
		UUID ob=new UUID(msb, Long.parseLong(lsb));
		return ob.toString().replace("-", "");
	}

}
