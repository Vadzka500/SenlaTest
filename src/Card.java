import Interfaces.ICard;

public class Card extends MainC implements ICard {

    private String numberCard;

    private String password;

    private int money;

    public Card(long id, String num, String pass, int m) {
        this.setId(id);
        this.numberCard = num;
        this.password = pass;
        this.money = m;
    }

    public String getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(String numberCard) {
        this.numberCard = numberCard;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public int getMoney() {
        return money;
    }

    @Override
    public boolean removeMoney(int money) {
            if (Main.existMoney < money) {
                System.out.println("Банкомат не содержит данное количество средств");
            }
            if (money <= this.money) {
                this.money -= money;
                Main.existMoney -= money;
                return true;
            } else{
                System.out.println("Данная сумма превышает количество средств на вашем счете");
                return false;
            }
    }

    @Override
    public boolean addMoney(int money) {
        if (money > 1_000_000){
            System.out.println("Большая сумма");
            return false;
        }
        else {
            Main.existMoney += money;
            this.money += money;
            return true;
        }
    }

    public void setMoney(int money) {
        this.money = money;
    }
}
