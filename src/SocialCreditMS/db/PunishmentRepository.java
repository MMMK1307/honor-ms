package SocialCreditMS.db;

import SocialCreditMS.Model.Punishment;
import SocialCreditMS.Util.TableNames;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.function.Function;

public class PunishmentRepository
        implements BaseRepository<Punishment> {

    @Override
    public Punishment getById(UUID id) {

        var tableData =
                getRaw(TableNames.Punishment);

        var punishmentData =
                tableData.getJSONObject(id.toString());

        return Punishment.createFromJson(
                punishmentData
        );
    }

    @Override
    public ArrayList<Punishment> getAll() {

        ArrayList<Punishment> punishments =
                new ArrayList<>();

        var tableData =
                getRaw(TableNames.Punishment);

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var punishment =
                    Punishment.createFromJson(
                            tableData.getJSONObject(key)
                    );

            punishments.add(punishment);
        }

        return punishments;
    }

    @Override
    public ArrayList<Punishment> getBy(
            Function<Punishment, Boolean> predicate
    ) {

        ArrayList<Punishment> punishments =
                new ArrayList<>();

        var tableData =
                getRaw(TableNames.Punishment);

        for (Iterator<String> it = tableData.keys(); it.hasNext();) {

            var key = it.next();

            var punishment =
                    Punishment.createFromJson(
                            tableData.getJSONObject(key)
                    );

            if(predicate.apply(punishment)) {
                punishments.add(punishment);
            }
        }

        return punishments;
    }
}