package utils;
import com.mongodb.client.*;

import org.bson.Document;

import java.util.Arrays;

public class MongoDBReader {
    public static int VJNREGCL300350Fees;
    public static int VJNREGGIVSP450Fees;
    public static int VJNRegCitationXFees;
    public static int VJMIDFees;
    public static int AHCitationXLSFees;
    public static int AHPhenom300Fees;
    public static int CL605Fees;
    public static int VJCL850fees;
    public static int VJCL604fees;
    public static int AsiaCL605Fees;
    public static int VJGLOBAL6000Fees;
    public static int AHFalcon7XFees;
    public static int VJGLobal7500Fees;
    public static int AsiaVJGLOBAL6000Fees;
    public static int AHLegacy500Fees;
    public static int AHPraetor600Fees;
    public static int VJNRegLightFees;
    public static int VJCL350Fees;
    public static int AsiaVJCL350Fees;
    public static int AHLegacy600Fees;
    public static int AHLegacy650Fees;
    public static int VJNREGCL300350OverNightFees;
    public static int VJNREGGIVSP450OverNightFees;
    public static int VJNCitationOvernightFees;



        public static void getMongoDBData() {
            // MongoDB connection URI
            String connectionString = "mongodb://jsappdev:T3sT0nly!nD3v2020@route-group-pricing.data.dev.internal:27017/?authSource=routeGroupPricing&authMechanism=SCRAM-SHA-1";

            // Connect to MongoDB
           //MongoClientURI uri = new MongoClientURI(connectionString);
            //MongoClient mongoClient = new MongoClient(uri);

            MongoClient mongoClient = MongoClients.create(connectionString);
                MongoDatabase database = mongoClient.getDatabase("routeGroupPricing");
                MongoCollection<Document> collection = database.getCollection("engineRedEyeFees");


            // Access specific database
            //MongoDatabase database = mongoClient.getDatabase("routeGroupPricing");

            // Access specific collection within the database
            //MongoCollection<Document> collection = database.getCollection("engineRedEyeFees");

            // Query for documents
            //Document query = new Document("aircraft_category","VJ-HEAVY");

            Document query = new Document(
                    "aircraft_category",
                    new Document(
                            "$in", Arrays.asList("VJ-N-SUPERMID","N-ULR","VJ-MID","AH CITATION XLS+","AH PHENOM 300","VJ-HEAVY","VJ-CL850","VJ-CL604","ASIA_HEAVY","ULR","AH FALCON 7X","VJ-GL7500","ASIA_ULR","AH LEGACY 500","AH PRAETOR 600","VJ-N-CITATION-X","LIGHT","VJ-SUPERMID","ASIA_SUPERMID","AH LEGACY 600","AH LEGACY 650")
                    )
            );

            // Execute query
            MongoCursor<Document> cursor = collection.find(query).iterator();

            // Iterate over the results
            try {
                while (cursor.hasNext()) {
                    Document doc = cursor.next();
                    if (doc.getString("dep_start_time") != null) {
                        if(doc.get("aircraft_category").toString().contains("VJ-N-SUPERMID") && doc.getString("dep_start_time").equalsIgnoreCase("06:00")){
                            VJNREGCL300350Fees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("N-ULR") && doc.getString("dep_start_time").equalsIgnoreCase("06:00")){
                            VJNREGGIVSP450Fees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("VJ-N-CITATION-X") && doc.getString("dep_start_time").equalsIgnoreCase("06:00")){
                            VJNRegCitationXFees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("VJ-MID") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJMIDFees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("AH CITATION XLS+") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHCitationXLSFees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("AH PHENOM 300") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHPhenom300Fees= doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("VJ-HEAVY") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                            CL605Fees = doc.getInteger("fee");
                        }
                        if(doc.get("aircraft_category").toString().contains("VJ-CL850") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJCL850fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("VJ-CL604") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJCL604fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("ASIA_HEAVY") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AsiaCL605Fees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("ULR") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJGLOBAL6000Fees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("AH FALCON 7X") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHFalcon7XFees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("VJ-GL7500") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJGLobal7500Fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("ASIA_ULR") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AsiaVJGLOBAL6000Fees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("AH LEGACY 500") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHLegacy500Fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("AH PRAETOR 600") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHPraetor600Fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("LIGHT") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJNRegLightFees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("VJ-SUPERMID") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             VJCL350Fees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("ASIA_SUPERMID") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AsiaVJCL350Fees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("AH LEGACY 600") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHLegacy600Fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("AH LEGACY 650") && doc.getString("dep_start_time").equalsIgnoreCase("23:00")){
                             AHLegacy650Fees= doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("VJ-N-SUPERMID") && doc.getString("dep_start_time").equalsIgnoreCase("00:00")){
                             VJNREGCL300350OverNightFees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("N-ULR") && doc.getString("dep_start_time").equalsIgnoreCase("00:00")){
                             VJNREGGIVSP450OverNightFees = doc.getInteger("fee");
                        }
                         if(doc.get("aircraft_category").toString().contains("VJ-N-CITATION-X") && doc.getString("dep_start_time").equalsIgnoreCase("00:00")){
                             VJNCitationOvernightFees= doc.getInteger("fee");
                        }
                        // Extract data from the document
                        /*int fieldValue = doc.getInteger("fee");
                        String start_time = doc.getString("dep_start_time");
                        String end_time = doc.getString("dep_end_time");
                        Object aircraftCategories = doc.get("aircraft_category");
                        aircraftCategories.toString();
                        System.out.println("aircraft categories: " + aircraftCategories);
                        System.out.println("fee: " + fieldValue);
                        System.out.println("start time: " + start_time);
                        System.out.println("end time: " + end_time);*/
                        // You can access other fields similarly


                    }

                }
                System.out.println("VJNRegSuperMidfees flights are: "+ VJNREGCL300350Fees);
                System.out.println("NULROvernightFees flights are: "+ VJNREGGIVSP450OverNightFees);
                System.out.println("LightFees flights are: "+ VJNRegLightFees);
                System.out.println("AHFalcon7XFees flights are: "+ AHFalcon7XFees);
                System.out.println("AHLegacy500Fees flights are: "+ AHLegacy500Fees);
                System.out.println("AHLegacy600Fees flights are: "+ AHLegacy600Fees);
                System.out.println("AHPraetor600Fees flights are: "+ AHPraetor600Fees);
                System.out.println("AHCitationXLSPlusFees flights are: "+ AHCitationXLSFees);
                System.out.println("AsiaHeavyfees flights are: "+ AsiaCL605Fees);
            } finally {
                // Close the cursor when done
                cursor.close();
            }

            // Close the MongoDB connection
            mongoClient.close();
        }
//LILIAN 06-02-25 begin
    public static void getMongoDBDataEngineRWAPremiumAirports() {
        // MongoDB connection URI
        String connectionString = "mongodb://jsappdev:T3sT0nly!nD3v2020@route-group-pricing.data.dev.internal:27017/?authSource=routeGroupPricing&authMechanism=SCRAM-SHA-1";

        MongoClient mongoClient = MongoClients.create(connectionString);
        MongoDatabase database = mongoClient.getDatabase("routeGroupPricing");
        MongoCollection<Document> collection = database.getCollection("engineRWAPremiumAirports");

        Document query = new Document(
                "airport",
                new Document(
                        "$in", Arrays.asList("KPHX","KSAN","KSFO","KASE","KDEN","KEGE","KSBS","KAPF","KBCT","KEYW","KMIA","KOPF","KPBI","KVRB","KATL","KPDK","KSAV","KORD","KMDW",
                        		"KDPA","KUGN","KPWK","KALE","KCCG","KHHF","KINK", "KKIP", "KMIF", "KPEQ", "KPRS", "KTDW","KCVG","KBOS","KBED","KHYA",
                        		"KMVY","KDTW","KIMT","KMNM","KOSC","KPLN","KSAW","KTVC", "KINL","KMSP","KRGK","KRRT","KTWM","KSTL","KEWR" )
                )
        );

        // Execute query
        MongoCursor<Document> cursor = collection.find(query).iterator();

        // Iterate over the results
        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                if (doc.getString("airport") != null) {
                    if(doc.get("airport").toString().contains("KPHX") ){
                        FeeKPHX = doc.getInteger("price");
                    } else if(doc.get("airport").toString().contains("KSAN") ){
                    	FeeKSAN = doc.getInteger("price");
                    } else if(doc.get("airport").toString().contains("KSFO") ){
                    	FeeKSFO = doc.getInteger("price");
                    } else if(doc.get("airport").toString().contains("KASE") ){
                    	FeeKASE = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KDEN") ){
                    	FeeKDEN = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KEGE") ){
                    	FeeKEGE = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KSBS") ){
                    	FeeKSBS = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KAPF") ){
                    	FeeKAPF = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KBCT") ){
                    	 FeeKBCT = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KEYW") ){
                    	 FeeKEYW = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMIA") ){
                    	 FeeKMIA = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KOPF") ){
                    	 FeeKOPF = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPBI") ){
                    	 FeeKPBI = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KVRB") ){
                    	 FeeKVRB = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KATL") ){
                    	 FeeKATL = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPDK") ){
                    	 FeeKPDK = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KSAV") ){
                    	 FeeKSAV = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KORD") ){
                    	 FeeKORD = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMDW") ){
                    	 FeeKMDW = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KDPA") ){
                    	 FeeKDPA = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KUGN") ){
                    	 FeeKUGN = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPWK") ){
                    	 FeeKPWK = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KALE") ){
                    	 FeeKALE = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KCCG") ){
                    	 FeeKCCG = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KHHF") ){
                    	 FeeKHHF = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KINK") ){
                    	 FeeKINK = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KKIP") ){
                    	 FeeKKIP = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMIF") ){
                    	 FeeKMIF = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPEQ") ){
                    	 FeeKPEQ = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPRS") ){
                    	 FeeKPRS = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KTDW") ){
                    	 FeeKTDW = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KCVG") ){
                    	 FeeKCVG = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KBOS") ){
                    	 FeeKBOS = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KBED") ){
                    	 FeeKBED = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KHYA") ){
                    	 FeeKHYA = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMVY") ){
                    	 FeeKMVY = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KDTW") ){
                    	 FeeKDTW = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KIMT") ){
                    	 FeeKIMT = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMNM") ){
                    	 FeeKMNM = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KOSC") ){
                    	 FeeKOSC = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KPLN") ){
                    	 FeeKPLN = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KSAW") ){
                    	 FeeKSAW = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KTVC") ){
                    	 FeeKTVC = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KINL") ){
                    	 FeeKINL = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KMSP") ){
                    	 FeeKMSP = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KRGK") ){
                    	 FeeKRGK = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KRRT") ){
                    	 FeeKRRT = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KTWM") ){
                    	 FeeKTWM = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KSTL") ){
                    	 FeeKSTL = doc.getInteger("price");
                    }else if(doc.get("airport").toString().contains("KEWR") ){
                    	 FeeKEWR = doc.getInteger("price");
                    }
                }

            }
            
        } finally {
            // Close the cursor when done
            cursor.close();
        }

        // Close the MongoDB connection
        mongoClient.close();
    }
    
    //LILIAN 06-02-25 end

}
