package pro1.reports.report5.reportDataModel;

public class ExamStats {
    public int realizedExamsCount;
    public int[] teacherIds;

    public ExamStats(int realizedExamsCount, int[] teacherIds) {
       this.teacherIds = teacherIds;
       this.realizedExamsCount = realizedExamsCount;
    }
}
