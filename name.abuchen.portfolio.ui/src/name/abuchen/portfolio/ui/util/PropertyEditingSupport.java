package name.abuchen.portfolio.ui.util;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public abstract class PropertyEditingSupport extends ColumnEditingSupport
{
    private PropertyDescriptor descriptor;

    public PropertyEditingSupport(Class<?> subjectType, String attributeName)
    {
        this.descriptor = descriptorFor(subjectType, attributeName);
    }

    protected PropertyDescriptor descriptor()
    {
        return descriptor;
    }

    private PropertyDescriptor descriptorFor(Class<?> subjectType, String attributeName)
    {
        try
        {
            PropertyDescriptor[] properties = Introspector.getBeanInfo(subjectType).getPropertyDescriptors();
            for (PropertyDescriptor p : properties)
                if (attributeName.equals(p.getName()))
                    return p;
            throw new RuntimeException(String.format("%s has no property named %s", subjectType //$NON-NLS-1$
                            .getName(), attributeName));
        }
        catch (IntrospectionException e)
        {
            throw new RuntimeException(e);
        }
    }

}
