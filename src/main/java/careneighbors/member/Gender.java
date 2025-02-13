package careneighbors.member;

public enum Gender {
    MALE,
    FEMALE;

    public static Gender fromString(String gender) {
        if (gender == null) {
            throw new IllegalArgumentException("Gender cannot be null");
        }

        return switch (gender.toUpperCase()) {
            case "남성" -> MALE;
            case "여성" -> FEMALE;
            default -> throw new IllegalArgumentException("Invalid gender: " + gender);
        };
    }
}
