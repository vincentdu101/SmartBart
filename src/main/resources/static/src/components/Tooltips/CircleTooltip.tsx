import * as React from "react";
import { ITooltipState, ITooltipProps } from "../../types/TooltipTypes";
import "./CircleTooltip.css";

export class CircleTooltip extends React.Component<ITooltipProps, ITooltipState> {

    constructor(props: any) {
        super(props);

        this.determinePosition = this.determinePosition.bind(this);
        this.outputTooltipClass = this.outputTooltipClass.bind(this);

        this.state = {
            text: "tewds",
            x: 0,
            y: 0,
            tooltipActive: false
        };
    } 

    public componentDidMount(): void {
        this.setState({});
    }

    public componentWillReceiveProps(nextProps: Readonly<any>): void {
        this.setState({
            x: nextProps.x, 
            y: nextProps.y, 
            text: nextProps.text,
            tooltipActive: nextProps.tooltipActive
        });
    }

    private outputTooltipClass(): string {
        return this.state.tooltipActive ? "circle-tooltip active" : "circle-tooltip";
    }

    private determinePosition(): {top: string, left: string} {
        return {
            left: (this.state.x - 500) + "px",
            top: (this.state.y - 100) + "px"
        }
    }

    public render(): JSX.Element {
        return (
            <div className={this.outputTooltipClass()} style={this.determinePosition()}>
                {this.state.text}
            </div>
        );
    }

}