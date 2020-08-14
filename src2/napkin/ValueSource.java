// $Id: ValueSource.java,v 1.2 2004/03/26 02:54:52 kcrca Exp $

package napkin;

public interface ValueSource {

    void randomize();

    double get();

    double generate();

    double getMid();

    double getRange();

    double getAdjust();
}

