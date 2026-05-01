package pro1.reports.report4;

import com.google.gson.Gson;
import pro1.DataSource;
import pro1.apiDataModel.ThesisList;
import pro1.reports.report4.reportDataModel.ThesisDuration;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;

public class ThesisDurationReporting {

    public static ThesisDuration[] GetReport(DataSource dataSource, String katedra, String[] years) {

        ThesisDuration[] result = new ThesisDuration[years.length];

        for (int i = 0; i < years.length; i++) {
            String year = years[i];

            var json = dataSource.getKvalifikacniPrace(year, katedra);
            var list = new Gson().fromJson(json, ThesisList.class);

            var validTheses = Arrays.stream(list.kvalifikacniPrace)
                    .filter(t -> t.datumZadani != null && t.datumOdevzdani != null)
                    .filter(t -> t.datumZadani.isValid() && t.datumOdevzdani.isValid());

            var durations = validTheses
                    .mapToLong(t -> ChronoUnit.DAYS.between(
                            t.datumZadani.toLocalDate(),
                            t.datumOdevzdani.toLocalDate()
                    ));

            double avg = durations.average().orElse(0);

            result[i] = new ThesisDuration(year, Math.round(avg));
        }

        return result;
    }
}