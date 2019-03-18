import java.util.*;

class Movie{
    String title;
    String director;
    ArrayList<String> actors = new ArrayList<>();
    Movie(){

    }
    Movie(String title, String director, ArrayList<String> actors){
        this.title = title;
        this.director = director;
        this.actors = actors;
    }

}

public class PCT_dataFrame {
    Set<String> allActors = new HashSet<>();
    Set<String> allDirectors = new HashSet<>();
    ArrayList<Movie> allMovies = new ArrayList<>();
    String popularActor;
    ArrayList<String> popularActorMovies = new ArrayList<>();
    String popularPairActor;
    ArrayList<String> popularPairActorMovies = new ArrayList<>();
    String popularDirctAndActor;
    ArrayList<String> popularDirctAndActorMovies = new ArrayList<>();


    PCT_dataFrame(){ }

    //title director actor1 actor2 actor3
    PCT_dataFrame(ArrayList<String> inList){
        for(String str:inList) {
            String[] strAry = str.split(" ");
            ArrayList actors = new ArrayList<>();
            actors.add(strAry[2]);
            actors.add(strAry[3]);
            actors.add(strAry[4]);

            allActors.add(strAry[2]);
            allActors.add(strAry[3]);
            allActors.add(strAry[4]);
            allDirectors.add(strAry[1]);
            Movie movie = new Movie(strAry[0], strAry[1], actors);
            allMovies.add(movie);
        }
    }

    public void setPopularActor(){
        HashMap<String,Integer> actorMovieNum = new HashMap<>();
        for(String actor : allActors){
            for(Movie movie: allMovies){
                if(movie.actors.contains(actor)){
                    if(actorMovieNum.containsKey(actor)){
                        actorMovieNum.put(actor,actorMovieNum.get(actor)+1);
                    }else{
                        actorMovieNum.put(actor,1);
                    }
                }
            }
        }

        //pick up the largest num
        int maxNum = 0;
        String actorName = "";
        for (String actor:actorMovieNum.keySet()) {
            if (actorMovieNum.get(actor) > maxNum) {
                maxNum = actorMovieNum.get(actor);
                actorName = actor;
            }
        }
        popularActor = actorName;

        //get movie list
        for(Movie movie: allMovies){
            if(movie.actors.contains(popularActor)){
                popularActorMovies.add(movie.title);
            }
        }
        //array list sort
        popularActorMovies.sort(null);
    }




    public void setPairActors(){
        HashMap<String,Integer> pairActorMovieNum = new HashMap<>();
        for(String actor : allActors){ // loop for all actors
            for(Movie movie: allMovies){
                if(movie.actors.contains(actor)){ // find which movies that actor had performed
                    for(String pairActor : movie.actors){ //find each pair actor
                        if (!pairActor.equals(actor)){
                            String pairKey = actor+","+pairActor;
                            if(pairActorMovieNum.containsKey(pairKey)){ // record movie num
                                pairActorMovieNum.put(pairKey,pairActorMovieNum.get(pairKey)+1);
                            }else{
                                pairActorMovieNum.put(pairKey,1);
                            }
                        }
                    }

                }
            }
        }

        //pick up the largest num
        int maxNum = 0;
        String actorNames = "";
        for (String pairActor:pairActorMovieNum.keySet()) {
            if (pairActorMovieNum.get(pairActor) > maxNum) {
                maxNum = pairActorMovieNum.get(pairActor);
                actorNames = pairActor;
            }
        }
        String name1 = actorNames.split(",")[0];
        String name2 = actorNames.split(",")[1];
        if(name1.compareTo(name2) > 0){
            actorNames = name2 + "," + name1;
        }
        popularPairActor = actorNames;

        //get movie list
        for(Movie movie: allMovies){
            if(movie.actors.contains(name1) && movie.actors.contains(name2)){
                popularPairActorMovies.add(movie.title);
            }
        }
        //array list sort
        popularPairActorMovies.sort(null);
    }


    public void setDirectorAndActors(){
        HashMap<String,Integer> DirectorAndActorMovieNum = new HashMap<>();
        for(String director : allDirectors){ // loop for all directors
            for(Movie movie: allMovies){
                if(movie.director.contains(director)){ // find which movies that directors had performed
                    for(String pairActor : movie.actors){ //find each pair actor
                        String pairKey = director+","+pairActor;
                        if(DirectorAndActorMovieNum.containsKey(pairKey)){ // record movie num
                            DirectorAndActorMovieNum.put(pairKey,DirectorAndActorMovieNum.get(pairKey)+1);
                        }else{
                            DirectorAndActorMovieNum.put(pairKey,1);
                        }
                    }

                }
            }
        }
        //pick up the largest num
        int maxNum = 0;
        String directAndactorNames = "";
        for (String pairActor:DirectorAndActorMovieNum.keySet()) {
            if (DirectorAndActorMovieNum.get(pairActor) > maxNum) {
                maxNum = DirectorAndActorMovieNum.get(pairActor);
                directAndactorNames = pairActor;
            }
        }

        popularDirctAndActor = directAndactorNames;
        String director = popularDirctAndActor.split(",")[0];
        String actor = popularDirctAndActor.split(",")[1];

        //get movie list
        for(Movie movie: allMovies){
            if(movie.director.equals(director) && movie.actors.contains(actor)){
                popularDirctAndActorMovies.add(movie.title);
            }
        }
        //array list sort
        popularDirctAndActorMovies.sort(null);
    }


    public String stringMovieList(ArrayList<String> movieList){
        String rtn = "";
        for(String movieName : movieList){
            rtn = rtn + movieName + ",";
        }
        rtn = rtn.substring(0,rtn.length()-1);
        return rtn;
    }

    public static void main(String[] args){
        ArrayList<String> dataList = new ArrayList<>();
        dataList.add("AAAA direct1 actor1 actor2 actor3");
        dataList.add("BBBB direct2 actor1 actor2 actor4");
        dataList.add("CCCC direct2 actor3 actor6 actor4");
        dataList.add("DDDD direct4 actor9 actor10 actor4");
        dataList.add("EEEE direct3 actor11 actor6 actor4");
        dataList.add("FFFF direct5 actor4 actor6 actor9");

        PCT_dataFrame dataFrame = new PCT_dataFrame(dataList);
        dataFrame.setPopularActor();
        dataFrame.setPairActors();
        dataFrame.setDirectorAndActors();
        String populorActorInfo = "(" + dataFrame.popularActor +":" + dataFrame.stringMovieList(dataFrame.popularActorMovies) + ")";
        String populorPairActorInfo = "(" + dataFrame.popularPairActor +":" + dataFrame.stringMovieList(dataFrame.popularPairActorMovies) + ")";
        String populorDirctActorInfo = "(" + dataFrame.popularDirctAndActor +":" + dataFrame.stringMovieList(dataFrame.popularDirctAndActorMovies) + ")";

        System.out.println(populorActorInfo);
        System.out.println(populorPairActorInfo);
        System.out.println(populorDirctActorInfo);

    }





}

