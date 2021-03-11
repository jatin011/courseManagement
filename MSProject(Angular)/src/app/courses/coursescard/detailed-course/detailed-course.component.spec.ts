import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailedCourseComponent } from './detailed-course.component';

describe('DetailedCourseComponent', () => {
  let component: DetailedCourseComponent;
  let fixture: ComponentFixture<DetailedCourseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetailedCourseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailedCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
