import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddQuestionPackageComponent } from './add-question-package.component';

describe('AddQuestionPackageComponent', () => {
  let component: AddQuestionPackageComponent;
  let fixture: ComponentFixture<AddQuestionPackageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddQuestionPackageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddQuestionPackageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
