package Calculator;

public class Traductor {

    private static Traductor traductor = null;

    public static Traductor getInstance() {
        if (traductor == null)
            traductor = new Traductor();
        return traductor;
    }

    public double strToDouble(String str) {
        org.mariuszgromada.math.mxparser.Expression expression = new org.mariuszgromada.math.mxparser.Expression(str);
        return expression.calculate();
    }
}


