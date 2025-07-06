import org.apache.mahout.cf.taste.eval.*;
import org.apache.mahout.cf.taste.impl.eval.*;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

public class Ai {

    public static void main(String[] args) {
        try {
            // Load data from CSV
            File file = new File("data.csv"); // or use full path
            DataModel model = new FileDataModel(file);

            // Define similarity metric
            UserSimilarity similarity = new PearsonCorrelationSimilarity(model);

            // Find similar users (neighborhood size = 2)
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);

            // Create recommender engine
            Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

            // Get recommendations for user ID 1 (change as needed)
            List<RecommendedItem> recommendations = recommender.recommend(1, 3);

            for (RecommendedItem recommendation : recommendations) {
                System.out.println("Recommended Item: " + recommendation.getItemID() +
                        " (Score: " + recommendation.getValue() + ")");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


