
package ru.javaops.masterjava.xml.schema;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "groupType", namespace = "http://javaops.ru", propOrder = {
        "valueGroup"
})
public class Group {

    @XmlValue
    protected String valueGroup;

    @XmlAttribute(name = "id", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlID
    @XmlSchemaType(name = "ID")
    protected String id;

    @XmlAttribute(name = "project", required = true)
    @XmlIDREF
    @XmlSchemaType(name = "IDREF")
    protected Project project;

    public String getValueGroup() {
        return valueGroup;
    }

    public void setValueGroup(String valueGroup) {
        this.valueGroup = valueGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project value) {
        this.project = value;
    }
}
