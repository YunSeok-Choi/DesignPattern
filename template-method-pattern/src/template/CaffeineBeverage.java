package template;

public abstract class CaffeineBeverage {

    // 템플릿 메소드
    public final String prepareRecipe() {
        StringBuilder result = new StringBuilder();
        result.append(boilWater()).append("\n");
        result.append(brew()).append("\n");
        result.append(pourInCup());
        if (customerWantsCondiments()) {
            result.append("\n").append(addCondiments());
        }
        return result.toString();
    }

    protected abstract String brew();

    protected abstract String addCondiments();

    protected String boilWater() {
        return "물 끓이는 중";
    }

    protected String pourInCup() {
        return "컵에 따르는 중";
    }

    // hook
    protected boolean customerWantsCondiments() {
        return true;
    }

}
