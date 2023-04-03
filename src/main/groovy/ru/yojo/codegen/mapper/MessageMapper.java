package ru.yojo.codegen.mapper;

import ru.yojo.codegen.domain.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.yojo.codegen.constants.ConstantsEnum.*;
import static ru.yojo.codegen.util.MapperUtil.*;

public class MessageMapper {

    public List<Message> mapMessagesToObjects(Map<String, Object> messages, LombokProperties lombokProperties, String messagePackage, String commonPackage) {
        List<Message> messageList = new ArrayList<>();
        messages.forEach((messageName, messageValues) -> {
            Map<String, Object> messageMap = castObjectToMap(messageValues);
            Message message = new Message();
            message.setMessageName(capitalize(messageName));
            message.setLombokProperties(lombokProperties);
            message.setMessageProperties(getProperties(messageMap));
            message.setMessagePackageName(messagePackage);
            message.setCommonPackageName(commonPackage);
            messageList.add(message);
        });
        return messageList;
    }

    private MessageProperties getProperties(Map<String, Object> messageMap) {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setName(getStringValueIfExistOrElseNull(NAME, messageMap));
        messageProperties.setTitle(getStringValueIfExistOrElseNull(TITLE, messageMap));
        messageProperties.setSummary(getStringValueIfExistOrElseNull(SUMMARY, messageMap));
        messageProperties.setPayload(getPayload(castObjectToMap(messageMap.get(PAYLOAD.getValue()))));

        return messageProperties;
    }

    @SuppressWarnings("all")
    private MessagePayload getPayload(Map<String, Object> payload) {
        if (getStringValueIfExistOrElseNull(PROPERTIES, payload) != null) {
            Map<String, Object> propertiesMap = castObjectToMap(payload.get(PROPERTIES.getValue()));
            List<MessageVariableProperties> variableProperties = new ArrayList<>();
            propertiesMap.forEach((pk, pv) -> {
                Map<String, Object> stringObjectMap = castObjectToMap(pv);
                MessageVariableProperties mvp = new MessageVariableProperties();
                mvp.setName(uncapitalize(pk));
                mvp.setType(getStringValueIfExistOrElseNull(TYPE, stringObjectMap));
                if (getStringValueIfExistOrElseNull(REFERENCE, stringObjectMap) != null) {
                    mvp.setReference(capitalize(getStringValueIfExistOrElseNull(REFERENCE, stringObjectMap).replaceAll(".+/", "")));
                }
                variableProperties.add(mvp);
            });
            return new MessagePayload(variableProperties);
        } else {
            MessageVariableProperties mvp = new MessageVariableProperties();
            mvp.setReference(capitalize(getStringValueIfExistOrElseNull(REFERENCE, payload).replaceAll(".+/", "")));
            return new MessagePayload(Collections.singletonList(mvp));
        }
    }
}
