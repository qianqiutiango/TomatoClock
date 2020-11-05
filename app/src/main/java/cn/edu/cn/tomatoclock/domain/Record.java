package cn.edu.cn.tomatoclock.domain;

public class Record {
    private String RecordName;
    private String RecordContent;

    public Record(String recordName, String recordContent) {
        RecordName = recordName;
        RecordContent = recordContent;
    }

    public Record() {
    }

    public String getRecordName() {
        return RecordName;
    }

    public void setRecordName(String recordName) {
        RecordName = recordName;
    }

    public String getRecordContent() {
        return RecordContent;
    }

    public void setRecordContent(String recordContent) {
        RecordContent = recordContent;
    }
}
