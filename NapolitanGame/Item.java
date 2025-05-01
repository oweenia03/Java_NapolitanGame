// 상품 하나를 표현하는 클래스
public class Item {
    public String realName;      // 실제 상품 이름 (ex: 크레파스)
    public String displayName;   // 사용자가 보는 기호 이름 (ex: "@#!$")
    public String cost;          // 해당 상품을 살 때 지불해야 하는 신체 부위 또는 생명

    // 생성자: 상품 이름을 받아 초기화
    public Item(String realName) {
        this.realName = realName;
    }

    // 사용자에게 보여질 displayName 설정
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    // 상품의 cost(가격) 설정
    public void setCost(String cost) {
        this.cost = cost;
    }
}