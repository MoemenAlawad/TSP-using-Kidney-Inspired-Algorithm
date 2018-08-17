package ka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KA 
    {

        public static void main(String[] args) 
            {
                int number_of_iteration = 13 ;    
                int map [][] = new int [10][10];
                Integer[] random_road = {0,1,2,3,4,5,6,7,8,9}; 
                float fr = 0 ; 
                int sum = 0 ; 
                for (int i = 0 ; i < 10 ; i++  )
                    map[i][i] = -1 ; 
                for(int i = 0 ; i < 10 ; i++)
                    for(int j = 0 ; j < 10  ; j++)
                        if(i!= j)
                        {
                            map [i][j] = (int)(Math.random()*1000) ;
                        }
                ArrayList<road> population = new ArrayList<road>();
                for(int i = 0 ; i < 10 ; i++)
                    {
                        Collections.shuffle(Arrays.asList(random_road));
                        int arr [] = new int[10];
                        for (int j = 0 ; j < 10 ; j++) arr[j] = random_road[j];    
                        //System.out.println(Arrays.toString(random_road));
                        population.add(new road(arr,map));
                    }
               // System.out.println("");
                
                sum = 0 ;     
                for(int i = 0 ; i < 10 ; i++)
                    {
                       sum += population.get(i).value_s();
                    } 
                fr  = sum/10;
                for(int i = 0 ; i < 10 ; i++)
                    {
                        if ( population.get(i).value_s() < fr)
                            population.get(i).group = "W";
                        else 
                            population.get(i).group = "FB";
                    }
                //Collections.sort(population);
                
                while(number_of_iteration-- != 0)
                { int iter = population.size() ;
                    for(int i = 0 ; i< iter ; i++)
                            {
                                road road_I = new road(generate(population.get(i).path),map);
                                if ( road_I.value_s() < fr)
                                    {
                                        road_I.group = "W";
                                        road road_new = new road(generate(road_I.path),map);
                                            if(road_new.value_s() > fr )
                                                {
                                                    Collections.shuffle(Arrays.asList(random_road));
                                                    int arr [] = new int[10];
                                                    for (int j = 0 ; j < 10 ; j++) arr[j] = random_road[j];
                                                    road roaD = new road(arr,map);
                                                    roaD.group ="W" ;
                                                    population.add(roaD);
                                                }
                                            else 
                                                {
                                                  road_new.group = "FB" ;
                                                  population.add(road_new);
                                                }
                                    }
                                
                                
                                else 
                                    {
                                        road_I.group = "FB";
                                        int worest = S_worest(population);
                                        if (road_I.value_s() < population.get(worest).value_s())
                                            {
                                                population.get(worest).group = "W";
                                                road_I.group = "FB" ; 
                                                population.add(road_I);
                                            }
                                        else 
                                            {
                                                road_I.group = "W" ;
                                                population.add(road_I);
                                            }        
                                    }   
                                                sum = 0 ;     
                                 for(int c = 0 ; c < 10 ; c++)
                                     {
                                        sum += population.get(c).value_s();
                                     } 
                                 fr  = sum/10;   
                                 //System.out.println(Arrays.toString(population.get(population.size()-1).path));
                            }
                }
                int b = S_best(population);
                System.out.println(Arrays.toString(population.get(b).path));
                System.out.println((population.get(b).value_s()));
                
                
            }
        public static int S_best(ArrayList<road> a)
            {
                int s = Integer.MAX_VALUE , index = -1;
                for(int i = 0 ; i < a.size() ;i++ )
                    {
                        if (a.get(i).value_s() < s && a.get(i).group.equals("FB"))
                            {
                                s = a.get(i).value_s() ;
                                index = i ;
                            }
                    }
                return index ; 
            }
        public static int S_worest(ArrayList<road> a)
            {
                int s = Integer.MIN_VALUE , index = -1;
                for(int i = 0 ; i < a.size() ;i++ )
                    {
                        if (a.get(i).value_s() > s && a.get(i).group.equals("FB"))
                            {
                                s = a.get(i).value_s() ;
                                index = i ;
                            }
                    }
                return index ; 
            }
    
        public static int[] generate(int [] x)
            {
                int a = (int)(Math.random()*10);
                int b = (int)(Math.random()*10);
                int temp = x[a];
                x[a] = x[b];
                x[b]=temp ;
                return x ;
            }    
}
