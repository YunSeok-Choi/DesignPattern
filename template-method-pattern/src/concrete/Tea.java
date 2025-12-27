package concrete;

import template.CaffeineBeverage;

public class Tea extends CaffeineBeverage {

	private boolean wantsCondiments;

	public Tea() {
		this.wantsCondiments = true;
	}

	public Tea(boolean wantsCondiments) {
		this.wantsCondiments = wantsCondiments;
	}

	@Override
	protected String brew() {
		return "차를 우려내는 중";
	}

	@Override
	protected String addCondiments() {
		return "레몬을 추가하는 중";
	}

	@Override
	protected boolean customerWantsCondiments() {
		return wantsCondiments;
	}

	public void setWantsCondiments(boolean wantsCondiments) {
		this.wantsCondiments = wantsCondiments;
	}
}
