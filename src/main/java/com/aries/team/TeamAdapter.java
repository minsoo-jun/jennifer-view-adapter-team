package com.aries.team;

import com.aries.team.entity.Message;
import com.aries.team.entity.Prop;
import com.aries.team.util.ConfUtil;
import com.aries.team.util.Client;
import com.aries.team.util.ConfUtilForLocal;
import com.aries.extension.data.EventData;
import com.aries.extension.handler.EventHandler;
import com.aries.extension.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by minsoo.jun on 2018-11-29.
 */
public class TeamAdapter implements EventHandler {
    /**
     * Format the date and time
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void on(EventData[] eventData) {
        LogUtil.info("On Envent #event=" + eventData.length);
        Client client;
        Prop teamProperties;
        EventData eventModel;
        Message message;
        try {

            teamProperties = ConfUtil.getTeamProperties();
            // for local testing.
            if(teamProperties.getUrl() == null){
                teamProperties = ConfUtilForLocal.getTeamProperties();
            }

            for (int i = 0; i < eventData.length; i++) {
                eventModel = (EventData) eventData[i];
                message = new Message(teamProperties, this.jenniferEventToString(eventModel), eventModel.eventLevel);
                client = new Client(message);
                client.push();
                LogUtil.info("Msg" + message.getText());
            }
        } catch (Exception e) {
            LogUtil.error(e.getMessage());
        }
    }

    /**
     *
     * @param eventModel
     * @return String message
     */
    private String jenniferEventToString(EventData eventModel) {
        StringBuilder messageBody = new StringBuilder();
        String newLine = "</br>";
        messageBody.append("{\"text\":\"");
        messageBody.append("<font color='red'>[" + eventModel.eventLevel +"]</font> The following event " + eventModel.errorType + "  was caught by JENNIFER").append(newLine);
        messageBody.append("Here are some additional details").append(newLine);
        messageBody.append("--------------------------------------------------------------- ").append(newLine);
        messageBody.append("Affected Domain [ID:NAME]").append(newLine);
        messageBody.append("Jennifer Server: <a href='http://" + System.getenv("hostname") + ":7900/dashboard/realtimeAdmin'>" + System.getenv("hostname") + "</a>").append(newLine);
        messageBody.append(eventModel.domainId + ":" + eventModel.domainName + "").append(newLine);
        messageBody.append("Affected Instance [ID:NAME]").append(newLine);
        messageBody.append(eventModel.instanceId + ":" + eventModel.instanceName).append(newLine);
        String message = eventModel.message.length() == 0 ? "None" : eventModel.message;
        messageBody.append("Message :");
        messageBody.append("<font color='red'>" + message + "</font>").append(newLine);
        String detailedMessage = eventModel.detailMessage.length() == 0 ? "None" : eventModel.detailMessage;

        messageBody.append("Detailed Message :").append(newLine);
        messageBody.append(detailedMessage);
        String serviceName = eventModel.serviceName.length() == 0 ? "Not available" : eventModel.serviceName;
        messageBody.append("Service Name :");
        messageBody.append(serviceName).append(newLine);
        String transactionId = eventModel.txid == 0 ? "Not available" : eventModel.txid + "";
        messageBody.append("Transaction Id :");
        messageBody.append(transactionId).append(newLine);
        String metricsName = eventModel.metricsName.length() == 0 ? "Not available" : eventModel.metricsName;
        messageBody.append("Metrics Name: ");
        messageBody.append(metricsName).append(newLine);
        Date d = new Date(eventModel.time);
        messageBody.append("Event Time [Raw:HumanRedable]").append(newLine);
        messageBody.append(eventModel.time + ":" + sdf.format(d)).append(newLine);;
        messageBody.append("---------------------------------------------------------------").append(newLine);
        messageBody.append("This message was created automatically by JENNIFER Team Adapter Manggu\"");
        messageBody.append("}");

        return messageBody.toString();
    }
}
