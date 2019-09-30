import Calculator.Traductor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class TestTraductor {

    @Test
    void oneInstanceTraductor() {
        System.out.println(" | TEST | > Calculator/Traductor [1] | Testing unique instanciation design pattern");
        Traductor view = Traductor.getInstance();
        assertEquals(Traductor.getInstance(), view);
    }

    @Test
    void basicCalcule() {
        System.out.println(" | TEST | > Calculator/Traductor [2] | Testing simple calcul");
        String calcule = "1 + 5 - 0 + 3";
        assertEquals(9,Traductor.getInstance().strToDouble(calcule));
    }

    @Test
    void complexCalcule() {
        System.out.println(" | TEST | > Calculator/Traductor [3] | Testing complex calcul");
        String calcule = "1 + 2 * 3";
        assertEquals(7, Traductor.getInstance().strToDouble(calcule));
    }

    @Test
    void ZeroDivision() {
        System.out.println(" | TEST | > Calculator/Traductor [4] | Testing zero division");
        String calcule = "5 / 0";
        assertEquals(true, Double.isNaN(Traductor.getInstance().strToDouble(calcule)));
    }
}