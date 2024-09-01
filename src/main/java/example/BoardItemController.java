package example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.kopo05.ctc.spring.board.domain.BoardItem;
import kr.ac.kopo05.ctc.spring.board.domain.CommentItem;
import kr.ac.kopo05.ctc.spring.board.domain.UserItem;
import kr.ac.kopo05.ctc.spring.board.dto.BoardForm;
import kr.ac.kopo05.ctc.spring.board.dto.PageMakerDTO;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepository;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.CommentItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;
import kr.ac.kopo05.ctc.spring.board.service.BoardService;


@Controller
@RequestMapping("/board")
public class BoardItemController {
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private UserItemRepository userItemRepository;
	
	@Autowired
	private BoardItemRepositoryCustomImpl boardItemRepositoryCustomImpl;
	
	@Autowired
	private CommentItemRepositoryCustomImpl commentItemRepositoryCustomImpl;
	
	@Autowired
    private BoardService boardService;
	
	@GetMapping(value = {"", "/"})
	public String showAllBoards(Model model, 
								@RequestParam(defaultValue = "0") int page,
								@RequestParam(defaultValue = "2") int size) {
		
		PageRequest pageable = PageRequest.of(page, size);
		Page<BoardItem> boardList = boardItemRepository.findAll(pageable);

		PageMakerDTO pageMaker = new PageMakerDTO(page, size, boardList.getTotalPages());
		
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("boardList", boardList);
		model.addAttribute("pageMaker.startPage", pageMaker.getStartPage() - 11);
		
		System.out.println(pageMaker.toString());
		
		return "/board/showBoard";
	}
	
	@GetMapping("/create")
	public String createNewForm() {
		return "board/new_form";
		}
	
	@PostMapping("/create")
	public String createNewBoardItem(BoardForm form) {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		UserItem userItem = userItemRepository.findById(form.getUserId()).orElse(null);
		
		BoardItem boardItem = new BoardItem(form.getTitle(), form.getContent(), dateFormat.format(today), userItem);
		
		boardItemRepository.save(boardItem);
		
		return"redirect:/board/";
	}
	
	@GetMapping("/{id}")
	public String showOneBoard(@PathVariable Long id, Model model) {
		BoardItem boardItem = boardItemRepositoryCustomImpl.selectById(id);
		
		List<CommentItem> commentList = commentItemRepositoryCustomImpl.selectById(id);
		
		model.addAttribute("boardItem", boardItem);
		model.addAttribute("commentList", commentList);
			
		return "board/showOneBoard";
	}
	
	@GetMapping("/{id}/edit")
	public String boardEditForm(@PathVariable Long id, Model model) {
		
		BoardItem boardItem = boardItemRepositoryCustomImpl.selectById(id);
		
		model.addAttribute("boardItem", boardItem);
		
		return "board/edit";
	}
	
	
	@PostMapping("/{id}/edit")
	public String boardUpdate(BoardForm form) {
		
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
		UserItem userItem = userItemRepository.findById(form.getUserId()).orElse(null);
		
		BoardItem newBoardItem = new BoardItem(form.getId(), form.getTitle(), form.getContent(), dateFormat.format(today), userItem);	

		BoardItem originBoardItem = boardItemRepositoryCustomImpl.selectById(form.getUserId());
		
		if(originBoardItem != null) {
			boardItemRepository.save(newBoardItem);
		}
		return "redirect:/board/" + newBoardItem.getId();
	}
	
	@GetMapping("/{id}/delete")
	public String boardDelete(@PathVariable Long id, RedirectAttributes rttr) {
		
		BoardItem boardItem = boardItemRepository.findById(id).orElse(null);
		
		if(boardItem != null){
			boardItemRepository.delete(boardItem);
			
			rttr.addFlashAttribute("message", "Delete Success!!");
		}
		return "redirect:/board/";
	}
}
