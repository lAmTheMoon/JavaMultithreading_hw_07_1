package hw_07_1.valueObgect;

public final class DurationMounts {
    private final int DURATION_MOUNTS;

    public DurationMounts(int durationMounts) {
        ensureDurationIsWithinValidRange(durationMounts);
        DURATION_MOUNTS = durationMounts;
    }

    private void ensureDurationIsWithinValidRange(int durationMounts) {
        int MAX_DURATION_MOUNTS = 84;
        int MIN_DURATION_MOUNTS = 6;
        if (durationMounts < MIN_DURATION_MOUNTS || durationMounts > MAX_DURATION_MOUNTS) {
            throw new IllegalArgumentException("Кредит предоставляется на срок от 6 месяцев до 7 лет.");
        }
    }

    public int getDurationMounts() {
        return DURATION_MOUNTS;
    }
}
