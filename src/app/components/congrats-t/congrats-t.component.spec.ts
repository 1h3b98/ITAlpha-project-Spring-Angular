import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CongratsTComponent } from './congrats-t.component';

describe('CongratsTComponent', () => {
  let component: CongratsTComponent;
  let fixture: ComponentFixture<CongratsTComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CongratsTComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CongratsTComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
