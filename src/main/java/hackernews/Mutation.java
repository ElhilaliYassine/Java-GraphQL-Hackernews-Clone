package hackernews;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;

public class Mutation implements GraphQLMutationResolver {

	private final LinkRepository linkRepository;

	public Mutation(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public Link createLink(String url, String description) {
		Link newLink = new Link(url, description);
		linkRepository.saveLink(newLink);
		return newLink;
	}
}
