public class DiscountCoupon {
    private String couponCode;
    private double discountAmount;
    private int maxUses;
    private int currentUses;

    public DiscountCoupon(String couponCode, double discountAmount, int maxUses) {
        this.couponCode = couponCode;
        this.discountAmount = discountAmount;
        this.maxUses = maxUses;
        this.currentUses = 0;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public boolean canUse() {
        return currentUses < maxUses;
    }

    public void useCoupon() {
        currentUses++;
    }

    @Override
    public String toString() {
        return couponCode + " - $" + discountAmount + " discount (Remaining Uses: " + (maxUses - currentUses) + ")";
    }
}