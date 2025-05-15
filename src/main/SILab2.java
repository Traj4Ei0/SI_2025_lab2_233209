public class SILab2 {
    public static double checkCart(List<Item> allItems, String cardNumber){//1
        if (allItems == null){//2
            throw new RuntimeException("allItems list can't be null!");//3
        }
        double sum = 0;//4
        for (int i = 0; i < allItems.size(); i++) {//5
            Item item = allItems.get(i);//6
            if (item.getName() == null  item.getName().length() == 0) {//7
                throw new RuntimeException("Invalid item!");//8
            }
            if (item.getPrice() > 300  item.getDiscount() > 0 || item.getQuantity() > 10) {//9
                sum -= 30;//10
            }
            if (item.getDiscount() > 0) {//11
                sum += item.getPrice() * (1 - item.getDiscount()) * item.getQuantity();//12
            } else {//13
                sum += item.getPrice() * item.getQuantity();//14
            }
        }//15
        if (cardNumber != null && cardNumber.length() == 16) {//16
            String allowed = "0123456789";//17
            char[] chars = cardNumber.toCharArray();//18
            for (int j = 0; j < cardNumber.length(); j++) {//19
                char c = cardNumber.charAt(j);//20
                if (allowed.indexOf(c) == -1) {//21
                    throw new RuntimeException("Invalid character in card number!");//22
                }
            }//23
        }
        else{//25
            throw new RuntimeException("Invalid card number!");//26
        }

        return sum;//27

    }//28
}
