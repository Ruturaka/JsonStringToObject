import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {
    public static void main(String[] args) {
        String[] json = {"{\"customer_name\": \"Rhythm\", \"items\": { \"description\": \"milk\", \"quantity\": 4 } }",
                "{\"customer_name\": \"Diksha\", \"items\": { \"description\": \"bread\", \"quantity\": 2 } }",
                "{\"customer_name\": \"Mark\", \"items\": { \"description\": \"bananas\", \"quantity\": 12 } }",
                "{\"customer_name\": \"Ankush\", \"items\": { \"description\": \"cereal\", \"quantity\": 1 } }"};

        try {
            String sql = "INSERT INTO sales VALUES (?, ?::JSON)";
            Connection conn= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jarvis", "postgres", "RPostgre17");
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i=0; i<4; i++) {
                ps.setInt (1, i+1);
                ps.setObject (2, json[i]);
                ps.executeUpdate();
            }
           //conn.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
