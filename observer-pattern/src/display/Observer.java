package display;

public interface Observer {

    void update(); // observer가 원하는 데이터만 가져오는 pull 방식

//    void update(float temp, float humidity, float pressure); // subject가 모든 데이터를 뿌리는 push 방식
}
