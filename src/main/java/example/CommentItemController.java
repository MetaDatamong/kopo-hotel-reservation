package example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import kr.ac.kopo05.ctc.spring.board.dto.CommentForm;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepository;
import kr.ac.kopo05.ctc.spring.board.repo.BoardItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.CommentItemRepository;
import kr.ac.kopo05.ctc.spring.board.repo.CommentItemRepositoryCustomImpl;
import kr.ac.kopo05.ctc.spring.board.repo.UserItemRepository;

@Controller
@RequestMapping("/comment")
public class CommentItemController {
	
	@Autowired
	private BoardItemRepository boardItemRepository;
	
	@Autowired
	private UserItemRepository userItemRepository;
	
	@Autowired
	private BoardItemRepositoryCustomImpl boardItemRepositoryCustomImpl;
	
	@Autowired
	private CommentItemRepository commentItemRepository;
	
	@Autowired
	private CommentItemRepositoryCustomImpl commentItemRepositoryCustomImpl;
	
	@PostMapping("/{boardId}/create")
	public String createNewComment(@PathVariable Long boardId, CommentForm form) {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		BoardItem boardItem = boardItemRepository.findById(boardId).orElse(null);
		
		CommentItem commentItem = new CommentItem(form.getContent(), dateFormat.format(today), form.getUserId(), boardItem);
	
		commentItemRepository.save(commentItem);
		
		return "redirect:/board/" + boardItem.getId();
	}
	
	@GetMapping("/{commentId}/edit")
	public String setEditModeTrut(@PathVariable Long commentId, Model model) {
		 // 특정 댓글을 가져옴
	    CommentItem commentItem = commentItemRepository.findById(commentId)
	            .orElseThrow(() -> new IllegalArgumentException("Invalid comment ID: " + commentId));

	    // 해당 댓글이 속한 게시물을 가져옴
	    Long boardItemId = commentItem.getBoardItem().getId();
	    
	    BoardItem boardItem = boardItemRepositoryCustomImpl.selectById(boardItemId);
	    
	    List<CommentItem> commentList = commentItemRepositoryCustomImpl.selectById(boardItemId);

	    // 모델에 데이터 추가
	    model.addAttribute("boardItem", boardItem);
	    model.addAttribute("commentList", commentList);

	    // 각 댓글의 editMode를 설정
	    for (CommentItem comment : commentList) {
	        if (comment.getId().equals(commentId)) {
	            comment.setEditMode(true);  // 이 댓글에 대해서만 editMode를 true로 설정
	        } else {
	            comment.setEditMode(false);
	        }
	    }
	    
	    return "board/showOneBoard";
	}
	
	@PostMapping("/{commentId}/edit")
	public String commentEditForm(@PathVariable Long commentId, 
								@RequestParam Long boardId, 
								CommentForm form) {
		
		Date today = new Date();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		BoardItem boardItem = boardItemRepositoryCustomImpl.selectById(boardId);
				
		CommentItem originCommentItem = commentItemRepository.findById(commentId).orElse(null);

		CommentItem newCommentItem = new CommentItem(commentId, form.getContent(), dateFormat.format(today), form.getUserId(), boardItem);
		
		if(originCommentItem != null) {
			commentItemRepository.save(newCommentItem);
		}
		return "redirect:/board/" + boardItem.getId();

	}
	
	@GetMapping("/{commentId}/delete")
	public String commentDelete(@PathVariable Long commentId, RedirectAttributes rttr) {
		CommentItem commentItem = commentItemRepository.findById(commentId).orElse(null);
		
		if(commentItem != null){
			commentItemRepository.delete(commentItem);
			
			rttr.addFlashAttribute("message", "Comment Delete Success!!");
		}
		return "redirect:/board/" + commentItem.getBoardItem().getId();
	}
}
