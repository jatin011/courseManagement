import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CoursescardComponent } from './coursescard.component';

describe('CoursescardComponent', () => {
  let component: CoursescardComponent;
  let fixture: ComponentFixture<CoursescardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CoursescardComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CoursescardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
