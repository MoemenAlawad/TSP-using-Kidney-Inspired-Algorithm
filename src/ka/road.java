package ka;

public class road implements Comparable
    {
        String group = "";
        int path [] ;
        int map [][] ;
        boolean Checked ;
        
        road (int path[] , int map[][])
            {
                this.path = path  ;
                this.map = map ;
            }
        public  int value_s()
            {
                int s = 0 ;
                for(int i = 0 ; i < (path.length-1) ; i++ )
                    s+=map[path[i]][path[i+1]];
                return s + map[path[path.length-1]][path[0]] ;
            }
    /*    @Override
        public int compareTo(road compare_road) {
            int compare_s=((road)compare_road).value_s();
                /* For Ascending order*/
               // return this.value_s()-compare_s;

            /* For Descending order do like this */
            //return compareage-this.studentage;
    //}

        @Override
        public int compareTo(Object compare_road)   
            {
                    int compare_s=((road)compare_road).value_s();
                    /* For Ascending order*/
                    return this.value_s()-compare_s;
            }
        }
