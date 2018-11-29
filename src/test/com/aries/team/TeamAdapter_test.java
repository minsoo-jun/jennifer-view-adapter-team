package com.aries.team;

import com.aries.team.TeamAdapter;
import com.aries.extension.data.EventData;
import com.aries.extension.data.InstanceData;

/**
 * Created by minsoo.jun on 2018-11-29.
 */
public class TeamAdapter_test {

    public static void main(String[] args) {
        try {

            EventData[] jm = new EventData[3];
            short domainId = 1;
            InstanceData instanceData = new InstanceData(1,"1","testinstance","1.1.1.1","java", "myhost","none");
            EventData je = new EventData(domainId, "event-search", 1541742709,304
                    , "event104","Test error Type", "Test MetricsName", "FATAL",  "Test Message</br>"
                    ,1, "oType", "detailMessage", "ServiceName", 11, "Nice Meeet you",instanceData  );
            jm[0]= je;

            je = new EventData(domainId, "event-search", 1641742709,304
                    , "event104","Test error Type", "Test MetricsName", "WARNING",  "Test Message2</br>"
                    ,1, "oType", "detailMessage", "ServiceName", 11, "Nice Meeet you",instanceData  );
            jm[1]= je;

            je = new EventData(domainId, "event-search", 1741742709,304
                    , "event104","Test error Type", "Test MetricsName", "NORMAL",  "Test Message2</br>"
                    ,1, "oType", "detailMessage", "ServiceName", 11, "Nice Meeet you",instanceData  );
            jm[2]= je;
            TeamAdapter ha = new TeamAdapter();
            ha.on(jm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
