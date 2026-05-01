package pro1.reports.report5;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.Book;
import pro1.apiDataModel.Exam;
import pro1.apiDataModel.ExamsList;
import pro1.reports.report5.reportDataModel.ExamStats;

public class DepartmentExamsStatsReporting {
    public static ExamStats GetReport(DataSource dataSource, String katedra)
    {
        String rawJson = dataSource.getTerminyZkousek2(katedra);
        var filledClass = new Gson().fromJson(rawJson, ExamsList.class);




        return new ExamStats(getExamCount(filledClass), getTeacherIds(filledClass));
    }

    private static int getExamCount(ExamsList filledClass) {
        return (int) filledClass.itemies.stream().filter(l -> l.studentCount > 0).count();
    }

    private static int[] getTeacherIds(ExamsList filledClass) {
    return filledClass.itemies.stream().mapToInt(l -> l.teachersId).filter(y -> y != 0 ).distinct().sorted().toArray();
    }
}
