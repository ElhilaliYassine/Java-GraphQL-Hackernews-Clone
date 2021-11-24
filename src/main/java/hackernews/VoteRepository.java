package hackernews;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.MongoCollection;

public class VoteRepository {

	private final MongoCollection<Document> votes;

	public VoteRepository(MongoCollection<Document> votes) {
		this.votes = votes;
	}

	public Vote findById(String id) {
		Document doc = votes.find(eq("_id", new ObjectId(id))).first();
		return vote(doc);
	}

	public List<Vote> findByLinkId(String linkId) {
		List<Vote> list = new ArrayList<>();
		for (Document doc : votes.find(eq("linkId", linkId))) {
			list.add(vote(doc));
		}
		return list;
	}

	public Vote saveVote(Vote vote) {
		Document doc = new Document();
		doc.append("userId", vote.getUserId());
		doc.append("linkId", vote.getLinkId());
		doc.append("createdAt", Scalars.dateTime.getCoercing().serialize(vote.getCreatedAt()));
		votes.insertOne(doc);
		return new Vote(
				doc.get("_id").toString(),
				ZonedDateTime.parse(doc.getString("createdAt")),
				doc.getString("userId"),
				doc.getString("linkId"));
	}

	private Vote vote(Document doc) {
		return new Vote(doc.get("_id").toString(), ZonedDateTime.parse(doc.getString("createdAt")),
				doc.getString("userId"), doc.getString("linkId"));
	}
}
