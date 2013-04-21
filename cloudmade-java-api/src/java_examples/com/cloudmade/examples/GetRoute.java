package com.cloudmade.examples;

/*
 * Example usage of CloudMade's API
 */

import com.cloudmade.api.CMClient;
import com.cloudmade.api.CMClient.MeasureUnit;
import com.cloudmade.api.CMClient.RouteType;
import com.cloudmade.api.geometry.Point;
import com.cloudmade.api.routing.Route;
import com.cloudmade.api.routing.RouteNotFoundException;
import com.cloudmade.api.geocoding.GeoResult;
import java.util.ArrayList;


public class GetRoute {
	
    public static void main(String[] args) {
        CMClient client = new CMClient("BC9A493B41014CAABB98F0471D759707");

        try {
            Route route = client.route(
                new Point(47.25976, 9.58423),
                new Point(47.66117, 9.99882),
                RouteType.CAR,
                null,
                null,
                "en",
                MeasureUnit.KM
            );

            System.out.println(route.summary.totalDistance);
            System.out.println(route.summary.startPoint);
            System.out.println(route.summary.endPoint);
            
            /*
             * 51.51589, -0.14068  51.51514, -0.14131
             */
            route = client.route(
            		new Point(51.51609, -0.1425),
                    new Point(51.5113, -0.14262),                   
                    RouteType.CAR,
                    null,
                    null,
                    "en",
                    MeasureUnit.KM
                );

                System.out.println(route.geometry.points);
                System.out.println(route.summary.totalDistance);
                
                ArrayList list = (ArrayList)route.geometry.points;
                String  path = "";
                char pt = 65;
                String label = "";
                for (int i=0; i<list.size(); i++) {
                	path = path+"|"+((Point)list.get(i)).lat+", "+((Point)list.get(i)).lon;
                	label = label+"&marker=size:mid|label:"+pt+"|"
                	          +((Point)list.get(i)).lat+", "+((Point)list.get(i)).lon;
                	++pt;
                }

                System.out.println("http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?size=1024x600&path=color:blue|weight:5"+path);
                System.out.println(label);                
                
                route = client.route(
                        new Point(51.5113, -0.14262),
                		new Point(51.51609, -0.1425),
                        RouteType.CAR,
                        null,
                        null,
                        "en",
                        MeasureUnit.KM
                    );

                    System.out.println(route.geometry.points);
                    System.out.println(route.summary.totalDistance);
                    
                    
                    list = (ArrayList)route.geometry.points;
                    path = "";
                    pt = 65;
                    label = "";
                    for (int i=0; i<list.size(); i++) {
                    	path = path+"|"+((Point)list.get(i)).lat+", "+((Point)list.get(i)).lon;
                    	label = label+"&marker=size:mid|label:"+pt+"|"
          	          +((Point)list.get(i)).lat+", "+((Point)list.get(i)).lon;
                    	++pt;

                    }

                    System.out.println("http://staticmaps.cloudmade.com/8ee2a50541944fb9bcedded5165f09d9/staticmap?size=1024x600&path=color:blue|weight:5"+path);
                    System.out.println(label);

                                                 //51.51409, -0.14011 and 51.51582,-0.14061  
 /*                                               route = client.route(
                                                		
                                                        //new Point(51.51589, -0.14068),
                                                		
                                                		new Point(51.51666,-0.14132),
                                                        new Point(51.51579,-0.14063),
                                                        RouteType.CAR,
                                                        null,
                                                        null,
                                                        "en",
                                                        MeasureUnit.KM
                                                    );

                                                    System.out.println(route.geometry.points);
                                                    System.out.println(route.summary.totalDistance);
*/
                                                   // GeoResult result = client.findClosest("cafe", new Point(51.51589, -0.14068));

                                                    //System.out.println(result.properties);
                                                    //System.out.println(result.centroid);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
