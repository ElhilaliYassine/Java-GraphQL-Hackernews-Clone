package hackernews;

import java.util.List;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;

public class Query implements GraphQLQueryResolver {

	private final LinkRepository linkRepository;

	public Query(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public List<Link> allLinks() {
		return linkRepository.getAllLinks();
	}

}
