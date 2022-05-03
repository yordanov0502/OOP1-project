@XmlJavaTypeAdapters({

        @XmlJavaTypeAdapter(type= LocalDate.class, value= LocalDateAdapter.class)

})


package bg.tu_varna.sit;

import org.joda.time.LocalDate;
import xml.LocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;