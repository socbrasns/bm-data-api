package com.cfg.bm.data.api.controller;

//@RestController
//@RequestMapping("/sub-habilities")
//@AllArgsConstructor(onConstructor_ = { @Autowired })
public class SubHabilityController {

//	private final SubHabilityRepository subHabilityRepository;
//	
//	private final HabilityRepository habilityRepository;
//
//	@GetMapping
//	public Flux<SubHability> findAll() {
//		return Flux.fromIterable(subHabilityRepository.findAll());
//	}
//
//	@GetMapping("/{id}")
//	public Mono<SubHability> findById(@PathVariable Long id) throws NotFoundException {
//		return Mono.just(subHabilityRepository.findById(id).orElseThrow(NotFoundException::new));
//	}
//
//	@PostMapping
//	public Mono<SubHability> save(@Valid @RequestBody SubHability subHability) {
//		subHability.setHability(habilityRepository.findById(subHability.getHability().getId())
//				.orElseThrow(EntityNotFoundException::new));
//		return Mono.just(subHabilityRepository.save(subHability));
//	}
//
//	@DeleteMapping("/{id}")
//	public Mono<Void> deleteById(@PathVariable Long id) {
//		return Mono.just(id).doOnNext(subHabilityRepository::deleteById).then();
//	}

}
