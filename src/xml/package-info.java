@XmlJavaTypeAdapters({

        @XmlJavaTypeAdapter(type= LocalDate.class, value=LocalDateAdapter.class)

})

package xml;

import org.joda.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;