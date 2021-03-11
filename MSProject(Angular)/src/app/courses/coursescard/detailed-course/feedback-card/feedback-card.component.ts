import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-feedback-card',
  templateUrl: './feedback-card.component.html',
  styleUrls: ['./feedback-card.component.scss']
})
export class FeedbackCardComponent implements OnInit {

  stars: number[] = [1, 2, 3, 4, 5];
    selectedValue: number;

  @Input()
  feedback;

  constructor() { }

  ngOnInit(): void {
    console.log(this.feedback)
    this.selectedValue=this.feedback.rating
  }


}
