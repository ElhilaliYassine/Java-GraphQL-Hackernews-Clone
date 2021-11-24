package hackernews;

import java.time.ZonedDateTime;

public class Vote {

	private final String id;
	private final ZonedDateTime createdAt;
	private final String userId;
	private final String linkId;

	public Vote(ZonedDateTime createdAt, String userId, String linkId) {
		this(null, createdAt, userId, linkId);
	}

	public Vote(String id, ZonedDateTime createdAt, String userId, String linkId) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.userId = userId;
		this.linkId = linkId;
	}

	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public String getId() {
		return id;
	}

	public String getLinkId() {
		return linkId;
	}

	public String getUserId() {
		return userId;
	}
}
