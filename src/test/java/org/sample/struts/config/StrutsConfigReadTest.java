package org.sample.struts.config;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.sample.struts.util.StrutsXmlParser;
import org.xml.sax.SAXException;

public class StrutsConfigReadTest {

    @Test
    public void testExecute() throws java.lang.Exception {
        String xml = "org/sample/struts/config/struts-config.xml";
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(xml)) {
            readTest(inputStream);
        }
    }

    private void readTest(InputStream inputStream) throws ParserConfigurationException, SAXException, JAXBException {
        assertThat(inputStream, notNullValue());
        StrutsConfig strutsConfig = StrutsXmlParser.parseStrutsConfig(inputStream);
        strutsConfig.getId();
        FormBeans formBeans = strutsConfig.getFormBeans();
        formBeans.getId();
        formBeans.getType();
        for (FormBean formBean : strutsConfig.getFormBeans().getFormBean()) {
            testFormBean(formBean);
        }
        testActionMappings(strutsConfig.getActionMappings());
        testController(strutsConfig.getController());
        testDataSources(strutsConfig.getDataSources());
        testGlobalExceptions(strutsConfig.getGlobalExceptions());
        testMessageResources(strutsConfig.getMessageResources());
        testPlugIn(strutsConfig.getPlugIn());
        testGlobalForwards(strutsConfig.getGlobalForwards());
    }

    private void testActionMappings(ActionMappings actionMappings) {
        if (actionMappings != null) {
            actionMappings.getId();
            actionMappings.getType();
            testAction(actionMappings.getAction());
        }
    }

    private void testAction(List<Action> actionList) {
        for (Action action : actionList) {
            action.getAttribute();
            action.getClassName();
            testDescription(action.getDescription());
            testDisplayName(action.getDisplayName());
            testException(action.getException());
            testForward(action.getForward());
            action.getIcon();
            action.getId();
            action.getInclude();
            action.getInput();
            action.getName();
            action.getParameter();
            assertThat(action.getPath(), notNullValue());
            action.getPrefix();
            action.getRoles();
            action.getScope();
            testSetProperties(action.getSetProperty());
            action.getSuffix();
            action.getType();
            action.getUnknown();
            action.getValidate();
        }
    }

    private void testGlobalForwards(GlobalForwards globalForwards) {
        if (globalForwards != null) {
            globalForwards.getId();
            globalForwards.getType();
            testForward(globalForwards.getForward());
        }
    }

    private void testForward(List<Forward> forwardList) {
        for (Forward forward : forwardList) {
            testForward(forward);
        }
    }

    private void testForward(Forward forward) {
        forward.getClassName();
        forward.getContextRelative();
        testIcon(forward.getIcon());
        forward.getId();
        testDescription(forward.getDescription());
        testDisplayName(forward.getDisplayName());
        assertThat(forward.getName(), notNullValue());
        assertThat(forward.getPath(), notNullValue());
        forward.getRedirect();
        testSetProperties(forward.getSetProperty());
    }

    private void testPlugIn(List<PlugIn> plugInList) {
        for (PlugIn plugIn : plugInList) {
            plugIn.getClassName();
            plugIn.getId();
            testSetProperties(plugIn.getSetProperty());
        }
    }

    private void testMessageResources(List<MessageResources> messageResourcesList) {
        for (MessageResources messageResources : messageResourcesList) {
            messageResources.getClassName();
            messageResources.getFactory();
            messageResources.getId();
            messageResources.getKey();
            messageResources.getNull();
            messageResources.getParameter();
            testSetProperties(messageResources.getSetProperty());
        }
    }

    private void testGlobalExceptions(GlobalExceptions globalExceptions) {
        if (globalExceptions != null) {
            globalExceptions.getId();
            testException(globalExceptions.getException());
        }
    }

    private void testException(List<Exception> exceptionList) {
        for (Exception exception : exceptionList) {
            testException(exception);
        }
    }

    private void testException(Exception exception) {
        exception.getBundle();
        exception.getClassName();
        exception.getHandler();
        testDescription(exception.getDescription());
        testDisplayName(exception.getDisplayName());
        exception.getId();
        exception.getKey();
        testSetProperties(exception.getSetProperty());
        exception.getPath();
        exception.getScope();
        exception.getType();
    }

    private void testDisplayName(DisplayName displayName) {
        if (displayName != null) {
            displayName.getId();
            displayName.getContent();
        }
    }

    private void testDescription(Description description) {
        if (description != null) {
            description.getId();
            description.getContent();
        }
    }

    private void testDataSources(DataSources dataSources) {
        if (dataSources != null) {
            dataSources.getId();
            for (DataSource dataSource : dataSources.getDataSource()) {
                dataSource.getClassName();
                dataSource.getId();
                dataSource.getKey();
                dataSource.getType();
                testSetProperties(dataSource.getSetProperty());
            }
        }
    }

    private void testSetProperties(List<SetProperty> setProperties) {
        for (SetProperty setProperty : setProperties) {
            testSetProperty(setProperty);
        }
    }

    private void testSetProperty(SetProperty setProperty) {
        setProperty.getId();
        assertThat(setProperty.getProperty(), notNullValue());
        assertThat(setProperty.getValue(), notNullValue());
    }

    private void testController(Controller controller) {
        if (controller != null) {
            controller.getBufferSize();
            controller.getClassName();
            controller.getContentType();
            controller.getDebug();
            controller.getForwardPattern();
            controller.getId();
            controller.getInputForward();
            controller.getLocale();
            controller.getMaxFileSize();
            controller.getMemFileSize();
            controller.getNocache();
            controller.getPagePattern();
            controller.getProcessorClass();
            controller.getTempDir();
            for (SetProperty setProperty : controller.getSetProperty()) {
                testSetProperty(setProperty);
            }
        }
    }

    private void testFormBean(FormBean formBean) {
        assertThat(formBean.getName(), notNullValue());
        assertThat(formBean.getType(), notNullValue());
        formBean.getClassName();
        formBean.getId();
        Icon icon = formBean.getIcon();
        testIcon(icon);
        testDescription(formBean.getDescription());
        testDisplayName(formBean.getDisplayName());
        formBean.getDynamic();
        for (FormProperty formProperty : formBean.getFormProperty()) {
            formProperty.getClassName();
            formProperty.getInitial();
            formProperty.getName();
            formProperty.getSize();
            formProperty.getType();
            testSetProperties(formProperty.getSetProperty());
        }
    }

    private void testIcon(Icon icon) {
        if (icon != null) {
            icon.getId();
            LargeIcon largeIcon = icon.getLargeIcon();
            if (largeIcon != null) {
                largeIcon.getId();
                largeIcon.getContent();
            }
            SmallIcon smallIcon = icon.getSmallIcon();
            if (smallIcon != null) {
                smallIcon.getId();
                smallIcon.getContent();
            }
        }
    }
}
