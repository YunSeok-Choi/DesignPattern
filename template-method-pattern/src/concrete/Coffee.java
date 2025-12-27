package concrete;

import template.CaffeineBeverage;

public class Coffee extends CaffeineBeverage {

	private boolean wantsCondiments;

	public Coffee() {
		this.wantsCondiments = true;
	}

	public Coffee(boolean wantsCondiments) {
		this.wantsCondiments = wantsCondiments;
	}

	@Override
	protected String brew() {
		return "필터로 커피를 우려내는 중";
	}

	@Override
	protected String addCondiments() {
		return "설탕과 우유를 추가하는 중";
	}

	@Override
	protected boolean customerWantsCondiments() {
		return wantsCondiments;
	}

	public void setWantsCondiments(boolean wantsCondiments) {
		this.wantsCondiments = wantsCondiments;
	}
}
