package adapter.jennifer.team;

import adapter.jennifer.team.entity.Message;
import adapter.jennifer.team.entity.Prop;
import adapter.jennifer.team.util.ConfUtil;
import adapter.jennifer.team.util.Client;
import com.jennifersoft.view.adapter.JenniferAdapter;
import com.jennifersoft.view.adapter.JenniferModel;
import com.jennifersoft.view.adapter.model.JenniferEvent;
import com.jennifersoft.view.adapter.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by minsoo.jun on 2018-10-24.
 */
public class TeamAdapter implements JenniferAdapter {
    /**
     * Format the date and time
     */
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void on(JenniferModel[] jennfierModel) {
        LogUtil.info("TeamAdapter : called");

        Client client;
        Prop teamProperties;
        JenniferEvent eventModel;
        Message message;
        try {
            LogUtil.info("JenniferModel length =" + jennfierModel.length);

            teamProperties = ConfUtil.getTeamProperties();

            for (int i = 0; i < jennfierModel.length; i++) {
                eventModel = (JenniferEvent) jennfierModel[i];
                message = new Message(teamProperties, this.jenniferEventToString(eventModel), eventModel.getEventLevel());
                client = new Client(message);
                client.push();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * String representation of the event to be used as the slack message body
     *
     * @param eventModel the event model
     * @param properties
     * @return event model as string (hipchat message body)
     */
    private String jenniferEventToString(JenniferEvent eventModel) {
        StringBuilder messageBody = new StringBuilder();
        String newLine = "</br>";
        messageBody.append("{\"text\":\"");
        messageBody.append("<font color='red'>[" + eventModel.getEventLevel() +"]</font> The following event " + eventModel.getErrorType() + "  was caught by JENNIFER").append(newLine);
        messageBody.append("Here are some additional details").append(newLine);
        messageBody.append("--------------------------------------------------------------- ").append(newLine);
        messageBody.append("Affected Domain [ID:NAME]").append(newLine);
        messageBody.append("Jennifer Server: <a href='http://" + System.getenv("hostname") + ":7900/dashboard/realtimeAdmin'>" + System.getenv("hostname") + "</a>").append(newLine);
        messageBody.append(eventModel.getDomainId() + ":" + eventModel.getDomainName() + "").append(newLine);
        messageBody.append("Affected Instance [ID:NAME]").append(newLine);
        messageBody.append(eventModel.getInstanceId() + ":" + eventModel.getInstanceName()).append(newLine);
        String message = eventModel.getMessage().length() == 0 ? "None" : eventModel.getMessage();
        messageBody.append("Message :");
        messageBody.append("<font color='red'>" + message + "</font>").append(newLine);
        String detailedMessage = eventModel.getDetailMessage().length() == 0 ? "None" : eventModel.getDetailMessage();

        messageBody.append("Detailed Message :").append(newLine);
        messageBody.append(detailedMessage);
        String serviceName = eventModel.getServiceName().length() == 0 ? "Not available" : eventModel.getServiceName();
        messageBody.append("Service Name :");
        messageBody.append(serviceName).append(newLine);
        String transactionId = eventModel.getTxId() == 0 ? "Not available" : eventModel.getTxId() + "";
        messageBody.append("Transaction Id :");
        messageBody.append(transactionId).append(newLine);
        String metricsName = eventModel.getMetricsName().length() == 0 ? "Not available" : eventModel.getMetricsName();
        messageBody.append("Metrics Name: ");
        messageBody.append(metricsName).append(newLine);
        Date d = new Date(eventModel.getTime());
        messageBody.append("Event Time [Raw:HumanRedable]").append(newLine);
        messageBody.append(eventModel.getTime() + ":" + sdf.format(d)).append(newLine);;
        messageBody.append("---------------------------------------------------------------").append(newLine);
        messageBody.append("This message was created automatically by JENNIFER Team Adapter (Manggu)\"");
        messageBody.append("}");

        return messageBody.toString();
    }
}
