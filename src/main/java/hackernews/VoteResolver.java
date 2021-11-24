package hackernews;

import com.coxautodev.graphql.tools.GraphQLResolver;

public class VoteResolver implements GraphQLResolver<Vote> {

	private UserRepository userRepository;

	private LinkRepository linkRepository;

	public VoteResolver(UserRepository userRepository, LinkRepository linkRepository) {
		this.userRepository = userRepository;
		this.linkRepository = linkRepository;
	}

	public User user(Vote vote) {
		return userRepository.findById(vote.getUserId());
	}

	public Link link(Vote vote) {
		return linkRepository.findById(vote.getLinkId());
	}
}
