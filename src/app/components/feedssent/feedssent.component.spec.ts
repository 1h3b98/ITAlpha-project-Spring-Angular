import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FeedssentComponent } from './feedssent.component';

describe('FeedssentComponent', () => {
  let component: FeedssentComponent;
  let fixture: ComponentFixture<FeedssentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FeedssentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FeedssentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
