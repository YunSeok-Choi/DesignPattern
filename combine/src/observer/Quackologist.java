package observer;

public class Quackologist implements Observer {

    @Override
    public String update(QuackObservable duck) {
        return "오리학자: " + duck + "이(가) 소리를 냈습니다.";
    }

    @Override
    public String toString() {
        return "오리학자";
    }
}
