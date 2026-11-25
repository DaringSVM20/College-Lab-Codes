public class GenericBoundedMethodDemo {
    
    public static<T extends Number> double sum(T a, T b) {
        return a.doubleValue() + b.doubleValue(); 
    }

    public static void main(String[] args) {
        System.out.println(sum(10,20));
        System.out.println(sum(5.5,2.5));
    }

}
