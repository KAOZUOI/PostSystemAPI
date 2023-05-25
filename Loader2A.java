import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.sql.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Loader2A{

  private static Connection con = null;
  private static PreparedStatement stmt = null;
  private static PreparedStatement stmt2 = null;
  final private static int T = 1000;


  private static void openDB(Properties prop) {
    try {
      Class.forName("org.postgresql.Driver");
    } catch (Exception e) {
      System.err.println("Cannot find the Postgres driver. Check CLASSPATH.");
      System.exit(1);
    }
    String url =
        "jdbc:postgresql://" + prop.getProperty("host") + ":"+prop.getProperty("port") + "/" + prop.getProperty("database");
    try {
      con = DriverManager.getConnection(url, prop);
//            if (con != null) {
//                System.out.println("Successfully connected to the database "
//                        + prop.getProperty("database") + " as " + prop.getProperty("user"));
//            }
    } catch (SQLException e) {
      System.err.println("Database connection failed");
      System.err.println(e.getMessage());
      System.exit(1);
    }
  }

  private static void closeDB() {
    if (con != null) {
      try {
        if (stmt != null) {
          stmt.close();
        }
        con.close();
        con = null;
      } catch (Exception ignored) {
      }
    }
  }

  private static Properties loadDBUser() {
    Properties properties = new Properties();
    try {
      properties.load(new InputStreamReader(new FileInputStream("resources/dbUser.properties")));
      return properties;
    } catch (IOException e) {
      System.err.println("can not find db user file");
      throw new RuntimeException(e);
    }
  }

  public static void setPrepareStatement1() {
    try {
      stmt = con.prepareStatement(
          "INSERT INTO post (author_id, content, posting_city, posting_country, posting_time, title) "
              +
              "VALUES (?,?,?,?,?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement2() {
    try {
      stmt = con.prepareStatement(
          "INSERT INTO author (author_id, authorname, phone_number, registration_time) " +
              "VALUES (?,?,?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement3() {
    try {
      stmt = con.prepareStatement("INSERT INTO follow (follower_author_id, following_author_id) " +
          "VALUES (?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement4() {
    try {
      stmt = con.prepareStatement("INSERT INTO post_favorite (author_author_id, post_id) " +
          "VALUES (?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }


  public static void setPrepareStatement5() {
    try {
      stmt = con.prepareStatement("INSERT INTO post_share (author_author_id, post_id) " +
          "VALUES (?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement6() {
    try {
      stmt = con.prepareStatement("INSERT INTO post_like (author_author_id, post_id) " +
          "VALUES (?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement7() {
    try {
      stmt = con.prepareStatement("INSERT INTO category (name) " +
          "VALUES (?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }


  public static void setPrepareStatement8() {
    try {
      stmt = con.prepareStatement("INSERT INTO reply (content, author_author_id, post_id) " +
          "VALUES (?,?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void setPrepareStatement9() {
    try {
      stmt = con.prepareStatement(
          "INSERT INTO post_category (post_id, category_id) " +
              "VALUES (?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }


  public static void setPrepareStatement10() {
    try {
      stmt2 = con.prepareStatement(
          "SELECT * FROM author WHERE authorname = ?;");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }
  public static void setPrepareStatement11() {
    try {
      stmt = con.prepareStatement(
          "INSERT INTO category (name) " +
              "VALUES (?);");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }
  public static void setPrepareStatement12() {
    try {
      stmt2 = con.prepareStatement(
          "SELECT * FROM category WHERE name = ?;");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }
  public static void setPrepareStatement13() {
    try {
      stmt2 = con.prepareStatement(
          "SELECT id FROM reply WHERE content = ?;");
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }
  public static void setPrepareStatement14() {
    try {
      stmt = con.prepareStatement("INSERT INTO reply (content, author_author_id, parent_id) " +
          "VALUES (?,?,?);");
    } catch (SQLException e) {
      System.err.println("Insert statement failed");
      System.err.println(e.getMessage());
      closeDB();
      System.exit(1);
    }
  }

  public static void main(String[] args) throws IOException, ParseException, SQLException {
    Properties prop = loadDBUser();

    int cnt = 0;

    ObjectMapper objectMapper = new ObjectMapper();
    JsonNode postsNode = objectMapper.readTree(new File("resources/posts.json"));

    long start = System.currentTimeMillis();

    openDB(prop);

//    setPrepareStatement2();
//    for (JsonNode postNode : postsNode) {
//      String id = postNode.get("Author's ID").asText();
//
//      String author = postNode.get("Author").asText();
//
//      String registration_time_str = postNode.get("Author Registration Time").asText();
//
//      String phone = postNode.get("Author's Phone").asText();
//
//      try {
//        stmt.setString(1, id);
//        stmt.setString(2, author);
//        stmt.setString(3, phone);
//        stmt.setString(4, registration_time_str);
//        stmt.addBatch();
//        cnt++;
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 2");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();

//    setPrepareStatement1();
//    for (JsonNode postNode : postsNode) {
//      String title = postNode.get("Title").asText();
//      String content = postNode.get("Content").asText();
//
//      String posting_time_str = postNode.get("Posting Time").asText();
//
//      String posting_position[] = postNode.get("Posting City").asText().split(", ");
//      String city = posting_position[0];
//      String country = posting_position[1];
//      String author = postNode.get("Author").asText();
//
//      try {
//        stmt.setString(1, author);
//        stmt.setString(2, content);
//        stmt.setString(3, city);
//        stmt.setString(4, country);
//        stmt.setString(5, posting_time_str);
//        stmt.setString(6, title);
//        stmt.addBatch();
//        cnt++;
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 1");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();

//    setPrepareStatement3();
//    for (JsonNode postNode : postsNode) {
//
//      String author_id = postNode.get("Author's ID").asText();
//
//      JsonNode follow_jns = postNode.get("Authors Followed By");
//      Map<JsonNode, String> follow_ids = new HashMap<>();
//      setPrepareStatement10();
//      for (JsonNode follow : follow_jns) {
//        stmt2.setString(1, follow.asText());
//        ResultSet rs = stmt2.executeQuery();
//        if (rs.next()) {
//          follow_ids.put(follow, rs.getString("author_id"));
//        }
//      }
//
//      try {
//        for (JsonNode follow : follow_jns) {
//          if(!follow_ids.containsKey(follow)) {
//            continue;
//          }
//          stmt.setString(1, author_id);
//          stmt.setString(2, follow_ids.get(follow));
//          stmt.addBatch();
//          cnt++;
//        }
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 3");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();

//    setPrepareStatement4();
//    for (JsonNode postNode : postsNode) {
//      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
//
//      JsonNode favorite_jns = postNode.get("Authors Who Favorited the Post");
//      JsonNode favorite_jn[] = new JsonNode[favorite_jns.size()];
//      int i = 0;
//      for (JsonNode favorite : favorite_jns) {
//        favorite_jn[i++] = favorite;
//      }
//      setPrepareStatement10();
//      Map<JsonNode, String> favorite_ids = new HashMap<>();
//      for (JsonNode favorite : favorite_jn) {
//        stmt2.setString(1, favorite.asText());
//        ResultSet rs = stmt2.executeQuery();
//        if (rs.next()) {
//          favorite_ids.put(favorite, rs.getString("author_id"));
//        }
//      }
//
//      try {
//        for (JsonNode favorite : favorite_jns) {
//          if(!favorite_ids.containsKey(favorite)) {
//            continue;
//          }
//          stmt.setString(1, favorite_ids.get(favorite));
//          stmt.setInt(2, post_id+3);
//          stmt.addBatch();
//          cnt++;
//        }
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 4");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();

//    setPrepareStatement5();
//    for (JsonNode postNode : postsNode) {
//      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
//
//      JsonNode share_jns = postNode.get("Authors Who Shared the Post");
//      JsonNode share_jn[] = new JsonNode[share_jns.size()];
//      int i = 0;
//      for (JsonNode share : share_jns) {
//        share_jn[i++] = share;
//      }
//      setPrepareStatement10();
//      Map<JsonNode, String> share_ids = new HashMap<>();
//      for (JsonNode share : share_jn) {
//        stmt2.setString(1, share.asText());
//        ResultSet rs = stmt2.executeQuery();
//        if (rs.next()) {
//          share_ids.put(share, rs.getString("author_id"));
//        }
//      }
//
//      try {
//        for (JsonNode share : share_jns) {
//          if(!share_ids.containsKey(share)) {
//            continue;
//          }
//          stmt.setString(1, share_ids.get(share));
//          stmt.setInt(2, post_id+3);
//          stmt.addBatch();
//          cnt++;
//        }
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 4");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();
//
//    setPrepareStatement6();
//    for (JsonNode postNode : postsNode) {
//      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
//
//      JsonNode like_jns = postNode.get("Authors Who Liked the Post");
//      JsonNode like_jn[] = new JsonNode[like_jns.size()];
//      int i = 0;
//      for (JsonNode like : like_jns) {
//        like_jn[i++] = like;
//      }
//      setPrepareStatement10();
//      Map<JsonNode, String> like_ids = new HashMap<>();
//      for (JsonNode like : like_jn) {
//        stmt2.setString(1, like.asText());
//        ResultSet rs = stmt2.executeQuery();
//        if (rs.next()) {
//          like_ids.put(like, rs.getString("author_id"));
//        }
//      }
//
//      try {
//        for (JsonNode like : like_jns) {
//          if(!like_ids.containsKey(like)) {
//            continue;
//          }
//          stmt.setString(1, like_ids.get(like));
//          stmt.setInt(2, post_id+3);
//          stmt.addBatch();
//          cnt++;
//        }
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 4");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//
//      if (cnt % T == 0) {
//        stmt.executeBatch();
//        stmt.clearBatch();
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();
//    setPrepareStatement11();
//    Set<String> categorySet = new HashSet<>();
//    for (JsonNode postNode : postsNode) {
//        JsonNode category_jns = postNode.get("Category");
//        for (JsonNode category : category_jns) {
//            categorySet.add(category.asText());
//        }
//    }
//    List<String> categoryList = new ArrayList<>(categorySet);
//    try {
//      for (String category : categoryList){
//        stmt.setString(1, category);
//        stmt.addBatch();
//        cnt++;
//      }
//    } catch (SQLException e) {
//      System.err.println("Insertion failed 11");
//      System.err.println(e.getMessage());
//      System.exit(1);
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();


//    setPrepareStatement9();
//    for (JsonNode postNode : postsNode) {
//      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
//
//      JsonNode category_jns = postNode.get("Category");
//      JsonNode category_jn[] = new JsonNode[category_jns.size()];
//      int i = 0;
//      for (JsonNode category : category_jns) {
//        category_jn[i++] = category;
//      }
//      setPrepareStatement12();
//      Map<JsonNode, Long> category_ids = new HashMap<>();
//      for (JsonNode category : category_jn) {
//        stmt2.setString(1, category.asText());
//        ResultSet rs = stmt2.executeQuery();
//        if (rs.next()) {
//          category_ids.put(category, rs.getLong("id"));
//        }
//      }
//      try {
//        for (JsonNode category : category_jns) {
//          stmt.setLong(1, post_id+3);
//          stmt.setLong(2, category_ids.get(category));
//          stmt.addBatch();
//          cnt++;
//        }
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 7");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//    }
//    stmt.executeBatch();
//    stmt.clearBatch();
//
//    long part1 = System.currentTimeMillis();

    JsonNode repliesNode = objectMapper.readTree(new File("resources/replies.json"));
//
//    for (JsonNode postNode : repliesNode) {
//      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
//      String content1 = postNode.get("Reply Content").asText();
//      String author1 = postNode.get("Reply Author").asText();
//      String author_id = null;
//      setPrepareStatement10();
//      stmt2.setString(1, author1);
//      ResultSet rs = stmt2.executeQuery();
//      if (rs.next()) {
//        author_id = rs.getString("author_id");
//      }
//      setPrepareStatement8();
//      try {
//        stmt.setString(1, content1);
//        stmt.setString(2, author_id);
//        stmt.setLong(3, post_id+3);
//        stmt.executeUpdate();
//      } catch (SQLException e) {
//        System.err.println("Insertion failed 8");
//        System.err.println(e.getMessage());
//        System.exit(1);
//      }
//    }


    for (JsonNode postNode : repliesNode) {
      int post_id = Integer.parseInt(postNode.get("Post ID").asText());
      String content1 = postNode.get("Reply Content").asText();
      String author1 = postNode.get("Reply Author").asText();
      String content2 = postNode.get("Secondary Reply Content").asText();
      String author2 = postNode.get("Secondary Reply Author").asText();
      setPrepareStatement10();
      String author_id = null;
      setPrepareStatement10();
      stmt2.setString(1, author1);
      ResultSet rs = stmt2.executeQuery();
      if (rs.next()) {
        author_id = rs.getString("author_id");
      }
      setPrepareStatement13();
      ResultSet rt = null;
      try {
        stmt2.setString(1, content1);
        rt = stmt2.executeQuery();
      } catch (SQLException e) {
        System.err.println("Insertion failed 10");
        System.err.println(e.getMessage());
        System.exit(1);
      }
      Long reply_id = 0L;
      if (rt.next()) {
        reply_id = rt.getLong(1);
      }
      setPrepareStatement14();
      try {

        stmt.setString(1, content2);
        stmt.setString(2, author_id);
        stmt.setLong(3,reply_id);
        stmt.executeUpdate();
      } catch (SQLException e) {
        System.err.println("Insertion failed 9");
        System.err.println(e.getMessage());
        System.exit(1);
      }
    }
    closeDB();
    long end = System.currentTimeMillis();
    System.out.println(cnt + " records successfully loaded");
    System.out.println("Loading speed : " + (cnt * 1000L) / (end - start) + " records/s");
//    System.out.println("Part 1 : " + (part1 - start) + " ms");
//    System.out.println("Part 2 : " + (end - part1) + " ms");
  }
}
