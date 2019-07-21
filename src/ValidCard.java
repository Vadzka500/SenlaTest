public class ValidCard {

    private String mNumber;

    public ValidCard(String n) {
        this.mNumber = n;
    }

    public boolean valid() {
        mNumber = mNumber.replaceAll("-", "");
        if (mNumber.length() != 16) return false;
        if (!isNumber()) return false;
        int sum = 0;
        for (int i = 0; i < mNumber.length(); i++) {
            int digit;
            if (i % 2 == 0) {
                digit = Character.getNumericValue(mNumber.charAt(i)) * 2;
                if (digit > 9) digit -= 9;
            } else digit = Character.getNumericValue(mNumber.charAt(i));
            sum += digit;
        }
        if (sum % 10 != 0) return false;
        return true;
    }

    private boolean isNumber() {
        for (char ch : mNumber.toCharArray()) {
            if (!Character.isDigit(ch)) return false;
        }
        return true;
    }

}
